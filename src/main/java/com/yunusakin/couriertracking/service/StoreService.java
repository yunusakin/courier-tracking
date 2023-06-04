package com.yunusakin.couriertracking.service;

import com.yunusakin.couriertracking.controller.data.request.CreateStoreRequest;
import com.yunusakin.couriertracking.model.Store;
import com.yunusakin.couriertracking.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getAllStore() {
        List<Store> storeList = new ArrayList<>();
        storeRepository.findAll().forEach(storeList::add);
        return storeList;
    }

    public Store createStore(CreateStoreRequest createStoreRequest) {
        Store store = new Store();
        store.setName(createStoreRequest.getName());
        store.setLatitude(createStoreRequest.getLatitude());
        store.setLongitude(createStoreRequest.getLongitude());
        return storeRepository.save(store);
    }

}
