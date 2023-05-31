package com.yunusakin.couriertracking.controller;

import com.yunusakin.couriertracking.controller.data.CreateStoreRequest;
import com.yunusakin.couriertracking.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StoreController {

    @PostMapping("/store/create")
    private ResponseEntity<Object> createStore(@RequestBody @Valid CreateStoreRequest createStoreRequest){
        try {
            return ResponseUtil.successHttpResponse();
        } catch (Exception e) {
            return ResponseUtil.errorResponse(e);
        }
    }
    @PostMapping("/store/list")
    private ResponseEntity<Object> storeList(){
        try {
            return ResponseUtil.successHttpResponse();
        } catch (Exception e) {
            return ResponseUtil.errorResponse(e);
        }
    }
}
