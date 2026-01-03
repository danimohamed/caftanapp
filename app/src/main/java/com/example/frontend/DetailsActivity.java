package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.frontend.api.ApiClient;
import com.example.frontend.api.ApiService;
import com.example.frontend.models.Caftan;
import com.example.frontend.models.SingleCaftanResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * DetailsActivity - Caftan Details Screen
 * Shows detailed information about a specific caftan
 * Allows user to proceed to rental form
 */
public class DetailsActivity extends AppCompatActivity {

    // Tag for logging
    private static final String TAG = "DetailsActivity";

    // UI Components
    private ImageView ivCaftanImage;
    private TextView tvCaftanName;
    private TextView tvCaftanSize;
    private TextView tvCaftanPrice;
    private Button btnRentNow;

    // API Service
    private ApiService apiService;

    // Current caftan
    private Caftan currentCaftan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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

        // Get caftan ID from intent
        int caftanId = getIntent().getIntExtra("caftan_id", -1);

        if (caftanId != -1) {
            // Load caftan details
            loadCaftanDetails(caftanId);
        } else {
            Toast.makeText(this, "Invalid caftan ID", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Set button click listener
        btnRentNow.setOnClickListener(v -> openRentForm());
    }

    /**
     * Initialize UI views
     */
    private void initViews() {
        ivCaftanImage = findViewById(R.id.ivCaftanImage);
        tvCaftanName = findViewById(R.id.tvCaftanName);
        tvCaftanSize = findViewById(R.id.tvCaftanSize);
        tvCaftanPrice = findViewById(R.id.tvCaftanPrice);
        btnRentNow = findViewById(R.id.btnRentNow);
    }

    /**
     * Load caftan details from API
     * @param id - Caftan ID
     */
    private void loadCaftanDetails(int id) {
        // Make API call
        Call<SingleCaftanResponse> call = apiService.getCaftan(id);

        call.enqueue(new Callback<SingleCaftanResponse>() {
            @Override
            public void onResponse(Call<SingleCaftanResponse> call, Response<SingleCaftanResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SingleCaftanResponse caftanResponse = response.body();

                    if (caftanResponse.isSuccess() && caftanResponse.getData() != null) {
                        // Get caftan data
                        currentCaftan = caftanResponse.getData();

                        // Display caftan details
                        displayCaftanDetails(currentCaftan);

                        Log.d(TAG, "Loaded caftan: " + currentCaftan.getName());
                    } else {
                        showError("Failed to load caftan details");
                        Log.e(TAG, "API error: " + caftanResponse.getMessage());
                    }
                } else {
                    showError("Failed to load data");
                    Log.e(TAG, "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SingleCaftanResponse> call, Throwable t) {
                showError("Network error. Please check your connection.");
                Log.e(TAG, "API call failed: " + t.getMessage(), t);
            }
        });
    }

    /**
     * Display caftan details in UI
     * @param caftan - Caftan object
     */
    private void displayCaftanDetails(Caftan caftan) {
        // Set text fields
        tvCaftanName.setText(caftan.getName());
        tvCaftanSize.setText(caftan.getSize());
        tvCaftanPrice.setText(caftan.getPrice() + " DH/day");

        // All caftans can be rented - button always enabled
        btnRentNow.setEnabled(true);

        // Load image with Glide
        if (caftan.getImageUrl() != null && !caftan.getImageUrl().isEmpty()) {
            Glide.with(this)
                    .load(caftan.getImageUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(ivCaftanImage);

            Log.d(TAG, "Loading image from: " + caftan.getImageUrl());
        } else {
            Log.e(TAG, "Image URL is null or empty");
            ivCaftanImage.setImageResource(R.drawable.ic_launcher_background);
        }
    }

    /**
     * Open rental form screen
     */
    private void openRentForm() {
        if (currentCaftan != null) {
            Intent intent = new Intent(DetailsActivity.this, RentActivity.class);
            intent.putExtra("caftan_id", currentCaftan.getId());
            intent.putExtra("caftan_name", currentCaftan.getName());
            intent.putExtra("caftan_price", currentCaftan.getPrice());
            startActivity(intent);
        }
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

