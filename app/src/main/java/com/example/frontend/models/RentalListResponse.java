package com.example.frontend.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Response model for list of rentals
 */
public class RentalListResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Rental> data;

    // Constructor
    public RentalListResponse() {
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Rental> getData() {
        return data;
    }

    public void setData(List<Rental> data) {
        this.data = data;
    }
}

