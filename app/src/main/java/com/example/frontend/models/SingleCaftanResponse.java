package com.example.frontend.models;

import com.google.gson.annotations.SerializedName;

/**
 * Response model for a single caftan
 * Matches the API response for GET /caftans/{id}
 */
public class SingleCaftanResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Caftan data;

    // Constructor
    public SingleCaftanResponse() {
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

    public Caftan getData() {
        return data;
    }

    public void setData(Caftan data) {
        this.data = data;
    }
}

