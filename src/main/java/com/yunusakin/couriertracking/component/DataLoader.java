package com.yunusakin.couriertracking.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunusakin.couriertracking.model.Courier;
import com.yunusakin.couriertracking.model.Store;
import com.yunusakin.couriertracking.repository.CourierRepository;
import com.yunusakin.couriertracking.repository.StoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final StoreRepository storeRepository;
    private final CourierRepository courierRepository;

    public DataLoader(StoreRepository storeRepository, CourierRepository courierRepository) {
        this.storeRepository = storeRepository;
        this.courierRepository = courierRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadStoreData();
        loadCourierData();
    }

    private void loadCourierData() throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/data/couriers.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        List<Courier> couriers = Arrays.asList(objectMapper.readValue(jsonData, Courier[].class));
        courierRepository.saveAll(couriers);
    }

    private void loadStoreData() throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/data/stores.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        List<Store> stores = Arrays.asList(objectMapper.readValue(jsonData, Store[].class));
        storeRepository.saveAll(stores);
    }
}
