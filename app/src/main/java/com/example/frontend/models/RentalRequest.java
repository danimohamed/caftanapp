package com.example.frontend.models;

import com.google.gson.annotations.SerializedName;

/**
 * Request model for creating a rental
 * This matches the backend API expectations
 */
public class RentalRequest {

    @SerializedName("customer_name")
    private String customerName;

    @SerializedName("caftan_id")
    private int caftanId;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("end_date")
    private String endDate;

    /**
     * Constructor
     * @param customerName - Customer's name
     * @param caftanId - ID of the caftan to rent
     * @param startDate - Start date in YYYY-MM-DD format
     * @param endDate - End date in YYYY-MM-DD format
     */
    public RentalRequest(String customerName, int caftanId, String startDate, String endDate) {
        this.customerName = customerName;
        this.caftanId = caftanId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
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

