package com.yunusakin.couriertracking.service;

import com.yunusakin.couriertracking.controller.data.request.CreateCourierRequest;
import com.yunusakin.couriertracking.model.Courier;
import com.yunusakin.couriertracking.repository.CourierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public Courier createCourier(CreateCourierRequest courierRequest) {
        Courier courier = new Courier();
        courier.setName(courierRequest.getName());
        courier.setToken(UUID.randomUUID().toString());
        return courierRepository.save(courier);
    }

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }
}
