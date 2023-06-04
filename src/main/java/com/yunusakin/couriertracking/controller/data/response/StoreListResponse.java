package com.yunusakin.couriertracking.controller.data.response;

import java.util.List;

public class StoreListResponse {
    private List<StoreInfo> stores;

    public StoreListResponse() {}

    public StoreListResponse(List<StoreInfo> stores) {
        this.stores = stores;
    }

    public List<StoreInfo> getStores() {
        return stores;
    }

    public void setStores(List<StoreInfo> stores) {
        this.stores = stores;
    }
}
