package com.yunusakin.couriertracking.controller;

import com.yunusakin.couriertracking.controller.data.CourierLogRequest;
import com.yunusakin.couriertracking.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourierController {

    @PostMapping("/courier/log")
    private ResponseEntity<Object> courierLog(@RequestBody CourierLogRequest courierLogRequest){
        try {
            return ResponseUtil.successHttpResponse();
        } catch (Exception e) {
            return ResponseUtil.errorResponse(e);
        }
    }

    @GetMapping("/courier/{courierToken}/distance")
    private ResponseEntity<Object> courierDistance(@PathVariable("courierToken") String courierToken){
        try {
            return ResponseUtil.successHttpResponse();
        } catch (Exception e) {
            return ResponseUtil.errorResponse(e);
        }
    }
}
