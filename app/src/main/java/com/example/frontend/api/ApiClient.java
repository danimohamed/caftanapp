package com.example.frontend.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiClient - Singleton class to manage Retrofit instance
 * This handles the connection to our backend API
 */
public class ApiClient {

    // Base URL for Android Emulator
    // Use http://10.0.2.2:8000/api to connect to localhost from emulator
    private static final String BASE_URL = "http://10.0.2.2:8000/api/";

    // Singleton instance
    private static Retrofit retrofit = null;

    /**
     * Get Retrofit instance
     * Creates a new instance if it doesn't exist
     * @return Retrofit instance
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            // Build Retrofit instance
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)  // Set base URL
                    .addConverterFactory(GsonConverterFactory.create())  // Add Gson converter
                    .build();
        }
        return retrofit;
    }

    /**
     * Get API Service instance
     * @return ApiService interface implementation
     */
    public static ApiService getApiService() {
        return getClient().create(ApiService.class);
    }
}

