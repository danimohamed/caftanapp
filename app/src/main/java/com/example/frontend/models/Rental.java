package com.example.frontend.models;

import com.google.gson.annotations.SerializedName;

/**
 * Rental model representing a caftan rental
 */
public class Rental {

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

    @SerializedName("total_price")
    private String totalPrice;

    @SerializedName("caftan")
    private Caftan caftan;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    // Constructor
    public Rental() {
    }

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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Caftan getCaftan() {
        return caftan;
    }

    public void setCaftan(Caftan caftan) {
        this.caftan = caftan;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

