package com.yunusakin.couriertracking.controller;

import com.yunusakin.couriertracking.controller.data.request.CreateCourierRequest;
import com.yunusakin.couriertracking.controller.data.response.CourierListResponse;
import com.yunusakin.couriertracking.controller.data.response.CreateCourierResponse;
import com.yunusakin.couriertracking.mapper.CourierMapper;
import com.yunusakin.couriertracking.model.Courier;
import com.yunusakin.couriertracking.service.CourierService;
import com.yunusakin.couriertracking.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/courier")
public class CourierController {

    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }


    @GetMapping("/list")
    public ResponseEntity<Object> getAllCouriers() {
        try {
            return ResponseUtil.successHttpResponse(new CourierListResponse(CourierMapper.convertCourierList(courierService.getAllCouriers())));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCourier(@Valid @RequestBody CreateCourierRequest createCourierRequest) {
        try {
            Courier courier = courierService.createCourier(createCourierRequest);
            return ResponseUtil.successHttpResponse(new CreateCourierResponse(courier.getName(),courier.getToken()));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(e);
        }
    }
}
