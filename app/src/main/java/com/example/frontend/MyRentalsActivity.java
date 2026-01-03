package com.example.frontend;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.adapters.RentalAdapter;
import com.example.frontend.api.ApiClient;
import com.example.frontend.api.ApiService;
import com.example.frontend.models.Rental;
import com.example.frontend.models.RentalListResponse;
import com.example.frontend.models.RentalResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MyRentalsActivity - Shows list of user's rentals
 * Displays all caftans that have been rented
 */
public class MyRentalsActivity extends AppCompatActivity {

    private static final String TAG = "MyRentalsActivity";

    // UI Components
    private RecyclerView rvRentals;
    private RentalAdapter rentalAdapter;

    // Data
    private List<Rental> rentalList;

    // API Service
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rentals);

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

        // Load rentals
        loadRentals();
    }

    /**
     * Initialize UI views
     */
    private void initViews() {
        rvRentals = findViewById(R.id.rvRentals);
        rvRentals.setLayoutManager(new LinearLayoutManager(this));

        // Initialize empty list
        rentalList = new ArrayList<>();

        // Setup adapter
        rentalAdapter = new RentalAdapter(this, rentalList);
        rvRentals.setAdapter(rentalAdapter);

        // Set delete click listener
        rentalAdapter.setOnDeleteClickListener((rental, position) -> {
            showDeleteConfirmation(rental, position);
        });
    }

    /**
     * Show confirmation dialog before deleting rental
     */
    private void showDeleteConfirmation(Rental rental, int position) {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Delete Rental")
                .setMessage("Are you sure you want to delete this rental?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    deleteRental(rental, position);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    /**
     * Delete rental from API
     */
    private void deleteRental(Rental rental, int position) {
        Call<RentalResponse> call = apiService.deleteRental(rental.getId());

        call.enqueue(new Callback<RentalResponse>() {
            @Override
            public void onResponse(Call<RentalResponse> call, Response<RentalResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RentalResponse rentalResponse = response.body();

                    if (rentalResponse.isSuccess()) {
                        // Remove from list
                        rentalList.remove(position);
                        rentalAdapter.notifyItemRemoved(position);
                        rentalAdapter.notifyItemRangeChanged(position, rentalList.size());

                        Toast.makeText(MyRentalsActivity.this,
                                "Rental deleted successfully", Toast.LENGTH_SHORT).show();

                        Log.d(TAG, "Rental deleted: " + rental.getId());

                        // Refresh list if empty
                        if (rentalList.isEmpty()) {
                            Toast.makeText(MyRentalsActivity.this,
                                    "No rentals yet", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showError("Failed to delete rental: " + rentalResponse.getMessage());
                        Log.e(TAG, "API error: " + rentalResponse.getMessage());
                    }
                } else {
                    showError("Failed to delete rental");
                    Log.e(TAG, "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RentalResponse> call, Throwable t) {
                showError("Network error. Please try again.");
                Log.e(TAG, "API call failed: " + t.getMessage(), t);
            }
        });
    }

    /**
     * Load rentals from API
     */
    private void loadRentals() {
        Call<RentalListResponse> call = apiService.getRentals();

        call.enqueue(new Callback<RentalListResponse>() {
            @Override
            public void onResponse(Call<RentalListResponse> call, Response<RentalListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RentalListResponse rentalResponse = response.body();

                    if (rentalResponse.isSuccess() && rentalResponse.getData() != null) {
                        // Update list
                        rentalList.clear();
                        rentalList.addAll(rentalResponse.getData());
                        rentalAdapter.notifyDataSetChanged();

                        Log.d(TAG, "Loaded " + rentalList.size() + " rentals");

                        if (rentalList.isEmpty()) {
                            Toast.makeText(MyRentalsActivity.this,
                                "No rentals yet", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        showError("No rentals found");
                        Log.e(TAG, "API error: " + rentalResponse.getMessage());
                    }
                } else {
                    showError("Failed to load rentals");
                    Log.e(TAG, "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RentalListResponse> call, Throwable t) {
                showError("Network error. Please check your connection.");
                Log.e(TAG, "API call failed: " + t.getMessage(), t);
            }
        });
    }

    /**
     * Show error message
     */
    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

