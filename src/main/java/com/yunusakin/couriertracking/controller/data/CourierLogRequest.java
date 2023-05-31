package com.yunusakin.couriertracking.controller.data;

import java.util.Date;

public class CourierLogRequest {
    private Date date;
    private CourierInfo courier;
    private double latitude;
    private double longitude;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
