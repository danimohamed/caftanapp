package com.example.frontend.api;

import com.example.frontend.models.CaftanResponse;
import com.example.frontend.models.RentalListResponse;
import com.example.frontend.models.RentalRequest;
import com.example.frontend.models.RentalResponse;
import com.example.frontend.models.SingleCaftanResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * API Service interface for Retrofit
 * Defines all API endpoints for the caftan rental application
 */
public interface ApiService {

    /**
     * Get all caftans
     * GET /caftans
     * @return List of all caftans
     */
    @GET("caftans")
    Call<CaftanResponse> getCaftans();

    /**
     * Get a single caftan by ID
     * GET /caftans/{id}
     * @param id - Caftan ID
     * @return Single caftan details
     */
    @GET("caftans/{id}")
    Call<SingleCaftanResponse> getCaftan(@Path("id") int id);

    /**
     * Create a rental
     * POST /rentals
     * @param request - Rental request with customer name, caftan ID, and dates
     * @return Rental response
     */
    @POST("rentals")
    Call<RentalResponse> createRental(@Body RentalRequest request);

    /**
     * Get all rentals
     * GET /rentals
     * @return List of all rentals
     */
    @GET("rentals")
    Call<RentalListResponse> getRentals();

    /**
     * Delete a rental
     * DELETE /rentals/{id}
     * @param id - Rental ID to delete
     * @return Delete response
     */
    @DELETE("rentals/{id}")
    Call<RentalResponse> deleteRental(@Path("id") int id);
}

