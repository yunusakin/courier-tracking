package com.yunusakin.couriertracking.controller;

import com.yunusakin.couriertracking.controller.data.request.CourierLogRequest;
import com.yunusakin.couriertracking.controller.data.response.CourierTotalDistanceResponse;
import com.yunusakin.couriertracking.service.TrackingService;
import com.yunusakin.couriertracking.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracking/")
public class TrackingController {
    private TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PostMapping("courier/log")
    private ResponseEntity<Object> courierLog(@RequestBody CourierLogRequest courierLogRequest) {
        try {
            trackingService.logCourier(courierLogRequest);
            return ResponseUtil.successHttpResponse();
        } catch (Exception e) {
            return ResponseUtil.errorResponse(e);
        }
    }

    @GetMapping("courier/{courierToken}/distance")
    private ResponseEntity<Object> courierTotalDistance(@PathVariable("courierToken") String courierToken) {
        try {
            return ResponseUtil.successHttpResponse(new CourierTotalDistanceResponse(trackingService.getTotalTravelDistance(courierToken)));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(e);
        }
    }
}
