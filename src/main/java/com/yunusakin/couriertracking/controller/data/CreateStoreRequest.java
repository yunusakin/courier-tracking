package com.yunusakin.couriertracking.controller.data;

import java.util.List;

public class CreateStoreRequest {
    private List<StoreInfo> stores;

    public List<StoreInfo> getStores() {
        return stores;
    }

    public void setStores(List<StoreInfo> stores) {
        this.stores = stores;
    }
}
