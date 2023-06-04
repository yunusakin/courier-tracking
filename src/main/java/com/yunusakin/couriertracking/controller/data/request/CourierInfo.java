package com.yunusakin.couriertracking.controller.data.request;

public class CourierInfo {
    private String courierToken;
    private String name;

    public CourierInfo() {
    }

    public CourierInfo(String courierToken, String name) {
        this.courierToken = courierToken;
        this.name = name;
    }

    public String getCourierToken() {
        return courierToken;
    }

    public void setCourierToken(String courierToken) {
        this.courierToken = courierToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
