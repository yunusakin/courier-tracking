package com.yunusakin.couriertracking.controller.data.response;

public class CourierTotalDistanceResponse {
    private Double distance;

    public CourierTotalDistanceResponse() {}

    public CourierTotalDistanceResponse(Double distance) {
        this.distance = distance;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
