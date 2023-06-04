package com.yunusakin.couriertracking.controller.data.request;

import java.time.LocalDateTime;

public class CourierLogRequest {
    private LocalDateTime time;
    private CourierInfo courier;
    private double latitude;
    private double longitude;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public CourierInfo getCourier() {
        return courier;
    }

    public void setCourier(CourierInfo courier) {
        this.courier = courier;
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
