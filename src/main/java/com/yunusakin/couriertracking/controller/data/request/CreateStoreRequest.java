package com.yunusakin.couriertracking.controller.data.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateStoreRequest {
    @NotBlank(message = "The name is required.")
    private String name;
    @NotNull(message = "latitude is  required.")
    private double latitude;
    @NotNull(message = "longitude is  required.")
    private double longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
