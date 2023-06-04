package com.yunusakin.couriertracking.mapper;

import com.yunusakin.couriertracking.controller.data.response.CourierInfo;
import com.yunusakin.couriertracking.controller.data.response.StoreInfo;
import com.yunusakin.couriertracking.model.Courier;
import com.yunusakin.couriertracking.model.Store;

import java.util.ArrayList;
import java.util.List;

public class CourierMapper {
    public static List<CourierInfo> convertCourierList(List<Courier> courierList) {
        List<CourierInfo> list = new ArrayList<>(courierList.size());
        for (Courier courier : courierList) {
            list.add(convertCourier(courier));
        }
        return list;
    }
    private static CourierInfo convertCourier(Courier courier) {
        CourierInfo courierInfo = new CourierInfo();
        courierInfo.setName(courier.getName());
        courierInfo.setToken(courier.getToken());
        return courierInfo;
    }
}
