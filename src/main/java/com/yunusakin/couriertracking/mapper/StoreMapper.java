package com.yunusakin.couriertracking.mapper;

import com.yunusakin.couriertracking.controller.data.response.StoreInfo;
import com.yunusakin.couriertracking.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreMapper {
    public static List<StoreInfo> convertStoreList(List<Store> storeList) {
        List<StoreInfo> list = new ArrayList<>(storeList.size());
        for (Store store : storeList) {
            list.add(convertStore(store));
        }
        return list;
    }
    private static StoreInfo convertStore(Store store) {
        StoreInfo storeInfo = new StoreInfo();
        storeInfo.setName(store.getName());
        storeInfo.setLatitude(store.getLatitude());
        storeInfo.setLongitude(store.getLongitude());
        return storeInfo;
    }
}
