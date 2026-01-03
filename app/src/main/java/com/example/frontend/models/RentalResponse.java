package com.example.frontend.models;

import com.google.gson.annotations.SerializedName;

/**
 * Response model for rental operations
 * Matches the API response for rental creation
 */
public class RentalResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private RentalData data;

    // Constructor
    public RentalResponse() {
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

    public RentalData getData() {
        return data;
    }

    public void setData(RentalData data) {
        this.data = data;
    }

    /**
     * Inner class representing rental data
     */
    public static class RentalData {
        @SerializedName("id")
        private int id;

        @SerializedName("customer_name")
        private String customerName;

        @SerializedName("caftan_id")
        private int caftanId;

        @SerializedName("start_date")
        private String startDate;

        @SerializedName("end_date")
        private String endDate;

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public int getCaftanId() {
            return caftanId;
        }

        public void setCaftanId(int caftanId) {
            this.caftanId = caftanId;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
    }
}

