package com.example.frontend.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Response model for list of caftans
 * Matches the API response for GET /caftans
 */
public class CaftanResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Caftan> data;

    // Constructor
    public CaftanResponse() {
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

    public List<Caftan> getData() {
        return data;
    }

    public void setData(List<Caftan> data) {
        this.data = data;
    }
}

