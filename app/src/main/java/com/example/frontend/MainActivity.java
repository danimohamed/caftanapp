package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.adapters.CaftanAdapter;
import com.example.frontend.api.ApiClient;
import com.example.frontend.api.ApiService;
import com.example.frontend.models.Caftan;
import com.example.frontend.models.CaftanResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MainActivity - Home Screen
 * Displays a list of all caftans (available and rented)
 * Fetches data from API and shows in RecyclerView
 * Supports price sorting and size filtering
 */
public class MainActivity extends AppCompatActivity {

    // Tag for logging
    private static final String TAG = "MainActivity";

    // UI Components
    private RecyclerView recyclerViewCaftans;
    private CaftanAdapter adapter;

    // API Service
    private ApiService apiService;

    // Data storage
    private List<Caftan> allCaftans = new ArrayList<>();
    private List<Caftan> filteredCaftans = new ArrayList<>();

    // Filter and sort state
    private String currentSizeFilter = "All";
    private String currentSortOption = "None";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Toolbar
        com.google.android.material.appbar.MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize API service
        apiService = ApiClient.getApiService();

        // Initialize RecyclerView
        setupRecyclerView();

        // Setup FAB for My Rentals
        com.google.android.material.floatingactionbutton.FloatingActionButton fabMyRentals = findViewById(R.id.fabMyRentals);
        fabMyRentals.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyRentalsActivity.class);
            startActivity(intent);
        });

        // Load caftans from API
        loadCaftans();
    }

    /**
     * Setup RecyclerView with adapter and layout manager
     */
    private void setupRecyclerView() {
        recyclerViewCaftans = findViewById(R.id.recyclerViewCaftans);

        // Set layout manager (vertical list)
        recyclerViewCaftans.setLayoutManager(new LinearLayoutManager(this));

        // Create and set adapter
        adapter = new CaftanAdapter(this);
        recyclerViewCaftans.setAdapter(adapter);

        // Set item click listener
        adapter.setOnItemClickListener(caftan -> {
            // Open details screen when caftan is clicked
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("caftan_id", caftan.getId());
            startActivity(intent);
        });
    }

    /**
     * Load caftans from API
     * Makes GET request to /caftans endpoint
     */
    private void loadCaftans() {
        // Make API call
        Call<CaftanResponse> call = apiService.getCaftans();

        call.enqueue(new Callback<CaftanResponse>() {
            @Override
            public void onResponse(Call<CaftanResponse> call, Response<CaftanResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Get the response
                    CaftanResponse caftanResponse = response.body();

                    if (caftanResponse.isSuccess() && caftanResponse.getData() != null) {
                        // Store the full list
                        allCaftans = new ArrayList<>(caftanResponse.getData());

                        // Apply current filters and sorting
                        applyFiltersAndSort();

                        Log.d(TAG, "Loaded " + allCaftans.size() + " caftans");
                    } else {
                        // API returned error
                        showError("Failed to load caftans");
                        Log.e(TAG, "API error: " + caftanResponse.getMessage());
                    }
                } else {
                    // Response not successful
                    showError("Failed to load data");
                    Log.e(TAG, "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CaftanResponse> call, Throwable t) {
                // Network error or other failure
                showError("Network error. Please check your connection.");
                Log.e(TAG, "API call failed: " + t.getMessage(), t);
            }
        });
    }

    /**
     * Show error message to user
     * @param message - Error message
     */
    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_my_rentals) {
            // Open My Rentals screen
            Intent intent = new Intent(MainActivity.this, MyRentalsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_refresh) {
            // Refresh caftan list
            loadCaftans();
            Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_sort) {
            // Show sort options dialog
            showSortDialog();
            return true;
        } else if (id == R.id.action_filter) {
            // Show filter options dialog
            showFilterDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Apply current filters and sorting to the caftan list
     */
    private void applyFiltersAndSort() {
        // Start with all caftans
        filteredCaftans = new ArrayList<>(allCaftans);

        // Apply size filter
        if (!currentSizeFilter.equals("All")) {
            List<Caftan> tempList = new ArrayList<>();
            for (Caftan caftan : filteredCaftans) {
                if (caftan.getSize() != null && caftan.getSize().equalsIgnoreCase(currentSizeFilter)) {
                    tempList.add(caftan);
                }
            }
            filteredCaftans = tempList;
        }

        // Apply sorting
        if (currentSortOption.equals("Price: Low to High")) {
            Collections.sort(filteredCaftans, new Comparator<Caftan>() {
                @Override
                public int compare(Caftan c1, Caftan c2) {
                    try {
                        double price1 = Double.parseDouble(c1.getPrice());
                        double price2 = Double.parseDouble(c2.getPrice());
                        return Double.compare(price1, price2);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                }
            });
        } else if (currentSortOption.equals("Price: High to Low")) {
            Collections.sort(filteredCaftans, new Comparator<Caftan>() {
                @Override
                public int compare(Caftan c1, Caftan c2) {
                    try {
                        double price1 = Double.parseDouble(c1.getPrice());
                        double price2 = Double.parseDouble(c2.getPrice());
                        return Double.compare(price2, price1);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                }
            });
        }

        // Update adapter
        adapter.setCaftanList(filteredCaftans);

        // Show result count
        Toast.makeText(this, "Showing " + filteredCaftans.size() + " caftan(s)", Toast.LENGTH_SHORT).show();
    }

    /**
     * Show sort options dialog
     */
    private void showSortDialog() {
        final String[] options = {"None", "Price: Low to High", "Price: High to Low"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tri par prix (Sort by Price)");
        builder.setItems(options, (dialog, which) -> {
            currentSortOption = options[which];
            applyFiltersAndSort();
            Toast.makeText(MainActivity.this, "Sorted by: " + currentSortOption, Toast.LENGTH_SHORT).show();
        });
        builder.show();
    }

    /**
     * Show filter options dialog
     */
    private void showFilterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter Options");

        String[] filterTypes = {"By Size", "Reset Filters"};

        builder.setItems(filterTypes, (dialog, which) -> {
            switch (which) {
                case 0: // By Size
                    showSizeFilterDialog();
                    break;
                case 1: // Reset Filters
                    resetFilters();
                    break;
            }
        });
        builder.show();
    }

    /**
     * Show size filter dialog
     */
    private void showSizeFilterDialog() {
        // Get unique sizes from all caftans
        List<String> sizes = new ArrayList<>();
        sizes.add("All");
        for (Caftan caftan : allCaftans) {
            if (caftan.getSize() != null && !sizes.contains(caftan.getSize())) {
                sizes.add(caftan.getSize());
            }
        }

        final String[] sizeArray = sizes.toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter by Size");
        builder.setItems(sizeArray, (dialog, which) -> {
            currentSizeFilter = sizeArray[which];
            applyFiltersAndSort();
            Toast.makeText(MainActivity.this, "Filter: Size " + currentSizeFilter, Toast.LENGTH_SHORT).show();
        });
        builder.show();
    }

    /**
     * Reset all filters and sorting
     */
    private void resetFilters() {
        currentSizeFilter = "All";
        currentSortOption = "None";
        applyFiltersAndSort();
        Toast.makeText(this, "Filters reset", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the list when returning to this activity
        loadCaftans();
    }
}

