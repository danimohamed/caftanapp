package com.example.frontend;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontend.api.ApiClient;
import com.example.frontend.api.ApiService;
import com.example.frontend.models.RentalRequest;
import com.example.frontend.models.RentalResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * RentActivity - Rental Form Screen
 * Allows users to submit rental request for a caftan
 * Sends POST request to create rental
 */
public class RentActivity extends AppCompatActivity {

    // Tag for logging
    private static final String TAG = "RentActivity";

    // UI Components
    private EditText etCustomerName;
    private EditText etStartDate;
    private EditText etEndDate;
    private Button btnSubmitRental;
    private android.widget.LinearLayout rentalInfoSection;
    private android.widget.TextView tvRentalDuration;
    private android.widget.TextView tvEstimatedPrice;

    // API Service
    private ApiService apiService;

    // Caftan info
    private int caftanId;
    private String caftanName;
    private double caftanPrice = 0;

    // Date tracking
    private Calendar selectedStartDate;
    private Calendar selectedEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        // Setup Toolbar with back button
        com.google.android.material.appbar.MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize API service
        apiService = ApiClient.getApiService();

        // Initialize views
        initViews();

        // Get caftan info from intent
        caftanId = getIntent().getIntExtra("caftan_id", -1);
        caftanName = getIntent().getStringExtra("caftan_name");
        String priceString = getIntent().getStringExtra("caftan_price");

        if (priceString != null) {
            try {
                caftanPrice = Double.parseDouble(priceString);
            } catch (NumberFormatException e) {
                caftanPrice = 0;
            }
        }

        if (caftanId == -1) {
            Toast.makeText(this, "Invalid caftan ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Set date picker listeners
        etStartDate.setOnClickListener(v -> showDatePicker(true));
        etEndDate.setOnClickListener(v -> showDatePicker(false));

        // Set button click listener
        btnSubmitRental.setOnClickListener(v -> submitRental());
    }

    /**
     * Show date picker dialog
     * @param isStartDate - true for start date, false for end date
     */
    private void showDatePicker(boolean isStartDate) {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // For end date, use start date as initial if available
        if (!isStartDate && selectedStartDate != null) {
            year = selectedStartDate.get(Calendar.YEAR);
            month = selectedStartDate.get(Calendar.MONTH);
            day = selectedStartDate.get(Calendar.DAY_OF_MONTH);
        }

        // Create date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            this,
            (view, selectedYear, selectedMonth, selectedDay) -> {
                // Create calendar for selected date
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(selectedYear, selectedMonth, selectedDay, 0, 0, 0);
                selectedDate.set(Calendar.MILLISECOND, 0);

                // Format date as YYYY-MM-DD
                String formattedDate = String.format(Locale.US, "%04d-%02d-%02d",
                    selectedYear, selectedMonth + 1, selectedDay);

                // Set to appropriate field
                if (isStartDate) {
                    selectedStartDate = selectedDate;
                    etStartDate.setText(formattedDate);
                    // Clear end date if it's before the new start date
                    if (selectedEndDate != null && selectedEndDate.before(selectedStartDate)) {
                        selectedEndDate = null;
                        etEndDate.setText("");
                        Toast.makeText(this, "End date cleared. Please select a new end date.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Validate end date is after start date
                    if (selectedStartDate != null && selectedDate.before(selectedStartDate)) {
                        Toast.makeText(this, "End date must be after start date", Toast.LENGTH_LONG).show();
                        return;
                    }
                    selectedEndDate = selectedDate;
                    etEndDate.setText(formattedDate);

                    // Calculate and show rental info
                    calculateAndShowRentalInfo();
                }
            },
            year, month, day
        );

        // Set minimum date
        if (isStartDate) {
            // Start date must be today or later
            datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        } else if (selectedStartDate != null) {
            // End date must be after start date
            Calendar minEndDate = (Calendar) selectedStartDate.clone();
            minEndDate.add(Calendar.DAY_OF_MONTH, 1); // At least 1 day rental
            datePickerDialog.getDatePicker().setMinDate(minEndDate.getTimeInMillis());
        } else {
            // If no start date selected, show error
            Toast.makeText(this, "Please select start date first", Toast.LENGTH_SHORT).show();
            return;
        }

        datePickerDialog.show();
    }

    /**
     * Calculate and display rental duration and estimated price
     */
    private void calculateAndShowRentalInfo() {
        if (selectedStartDate != null && selectedEndDate != null) {
            // Calculate duration in days
            long diffInMillis = selectedEndDate.getTimeInMillis() - selectedStartDate.getTimeInMillis();
            long days = diffInMillis / (1000 * 60 * 60 * 24);

            // Calculate estimated price
            double estimatedPrice = days * caftanPrice;

            // Show rental info section
            rentalInfoSection.setVisibility(android.view.View.VISIBLE);
            tvRentalDuration.setText("Duration: " + days + " day" + (days > 1 ? "s" : ""));
            tvEstimatedPrice.setText(String.format(Locale.US, "Estimated Total: %.2f DH", estimatedPrice));
        } else {
            rentalInfoSection.setVisibility(android.view.View.GONE);
        }
    }

    /**
     * Initialize UI views
     */
    private void initViews() {
        etCustomerName = findViewById(R.id.etCustomerName);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        btnSubmitRental = findViewById(R.id.btnSubmitRental);
        rentalInfoSection = findViewById(R.id.rentalInfoSection);
        tvRentalDuration = findViewById(R.id.tvRentalDuration);
        tvEstimatedPrice = findViewById(R.id.tvEstimatedPrice);
    }

    /**
     * Validate form inputs
     * @return true if valid, false otherwise
     */
    private boolean validateInputs() {
        String customerName = etCustomerName.getText().toString().trim();
        String startDate = etStartDate.getText().toString().trim();
        String endDate = etEndDate.getText().toString().trim();

        // Check if fields are empty
        if (customerName.isEmpty()) {
            etCustomerName.setError("Please enter customer name");
            etCustomerName.requestFocus();
            return false;
        }

        if (customerName.length() < 3) {
            etCustomerName.setError("Name must be at least 3 characters");
            etCustomerName.requestFocus();
            return false;
        }

        if (startDate.isEmpty()) {
            etStartDate.setError("Please select start date");
            Toast.makeText(this, "Please select a start date", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (endDate.isEmpty()) {
            etEndDate.setError("Please select end date");
            Toast.makeText(this, "Please select an end date", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Basic date format validation (YYYY-MM-DD)
        if (!startDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            etStartDate.setError("Invalid date format");
            return false;
        }

        if (!endDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            etEndDate.setError("Invalid date format");
            return false;
        }

        // Advanced date validation
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            sdf.setLenient(false);

            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            Date today = new Date();

            // Reset time part for accurate comparison
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            today = cal.getTime();

            // Check if start date is in the past
            if (start.before(today)) {
                Toast.makeText(this, "Start date cannot be in the past", Toast.LENGTH_LONG).show();
                return false;
            }

            // Check if end date is before start date
            if (end.before(start)) {
                Toast.makeText(this, "End date must be after start date", Toast.LENGTH_LONG).show();
                return false;
            }

            // Check if end date is same as start date
            if (end.equals(start)) {
                Toast.makeText(this, "Rental must be at least 1 day. Please select different dates.", Toast.LENGTH_LONG).show();
                return false;
            }

            // Calculate rental duration
            long diffInMillis = end.getTime() - start.getTime();
            long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);

            if (diffInDays > 30) {
                Toast.makeText(this, "Maximum rental period is 30 days", Toast.LENGTH_LONG).show();
                return false;
            }

        } catch (ParseException e) {
            Toast.makeText(this, "Invalid date format. Please select dates using the date picker.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    /**
     * Submit rental request to API
     */
    private void submitRental() {
        // Validate inputs first
        if (!validateInputs()) {
            return;
        }

        // Get form data
        String customerName = etCustomerName.getText().toString().trim();
        String startDate = etStartDate.getText().toString().trim();
        String endDate = etEndDate.getText().toString().trim();

        // Disable button to prevent multiple submissions
        btnSubmitRental.setEnabled(false);
        btnSubmitRental.setText("Submitting...");

        // Create rental request object
        RentalRequest request = new RentalRequest(customerName, caftanId, startDate, endDate);

        // Make API call
        Call<RentalResponse> call = apiService.createRental(request);

        call.enqueue(new Callback<RentalResponse>() {
            @Override
            public void onResponse(Call<RentalResponse> call, Response<RentalResponse> response) {
                // Re-enable button
                btnSubmitRental.setEnabled(true);
                btnSubmitRental.setText(R.string.submit_rental);

                if (response.isSuccessful() && response.body() != null) {
                    RentalResponse rentalResponse = response.body();

                    if (rentalResponse.isSuccess()) {
                        // Success!
                        Toast.makeText(RentActivity.this,
                            "Rental created successfully!",
                            Toast.LENGTH_LONG).show();

                        Log.d(TAG, "Rental created: " + rentalResponse.getMessage());

                        // Go back to main screen
                        finish();
                    } else {
                        // API returned error
                        showError("Failed to create rental: " + rentalResponse.getMessage());
                        Log.e(TAG, "API error: " + rentalResponse.getMessage());
                    }
                } else {
                    // Response not successful
                    if (response.code() == 422) {
                        showError("Validation error. Please check your inputs.");
                    } else if (response.code() == 400) {
                        showError("This caftan is not available for the selected dates.");
                    } else {
                        showError("Failed to create rental");
                    }
                    Log.e(TAG, "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RentalResponse> call, Throwable t) {
                // Re-enable button
                btnSubmitRental.setEnabled(true);
                btnSubmitRental.setText(R.string.submit_rental);

                // Network error
                showError("Network error. Please check your connection.");
                Log.e(TAG, "API call failed: " + t.getMessage(), t);
            }
        });
    }

    /**
     * Show error message
     * @param message - Error message
     */
    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle back button press
        finish();
        return true;
    }
}

