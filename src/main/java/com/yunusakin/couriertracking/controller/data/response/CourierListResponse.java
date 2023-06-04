package com.yunusakin.couriertracking.controller.data.response;

import java.util.List;

public class CourierListResponse {
    private List<CourierInfo> courierList;

    public CourierListResponse() {}

    public CourierListResponse(List<CourierInfo> courierList) {
        this.courierList = courierList;
    }

    public List<CourierInfo> getCourierList() {
        return courierList;
    }

    public void setCourierList(List<CourierInfo> courierList) {
        this.courierList = courierList;
    }
}
