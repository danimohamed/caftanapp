package com.example.frontend.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a Caftan
 * This matches the JSON structure from the backend API
 */
public class Caftan {

    // Fields match the API response
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("size")
    private String size;

    @SerializedName("price")
    private String price;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("availability")
    private boolean availability;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    // Constructor
    public Caftan() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
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

