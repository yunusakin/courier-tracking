package com.yunusakin.couriertracking.controller;

import com.yunusakin.couriertracking.controller.data.request.CreateStoreRequest;
import com.yunusakin.couriertracking.controller.data.response.StoreListResponse;
import com.yunusakin.couriertracking.mapper.StoreMapper;
import com.yunusakin.couriertracking.service.StoreService;
import com.yunusakin.couriertracking.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/list")
    private ResponseEntity<Object> storeList() {
        try {
            return ResponseUtil.successHttpResponse(new StoreListResponse(StoreMapper.convertStoreList(storeService.getAllStore())));
        } catch (Exception e) {
            return ResponseUtil.errorResponse(e);
        }
    }

    @PostMapping("/create")
    private ResponseEntity<Object> createStore(@Valid  @RequestBody CreateStoreRequest createStoreRequest) {
        try {
            storeService.createStore(createStoreRequest);
            return ResponseUtil.successHttpResponse();
        } catch (Exception e) {
            return ResponseUtil.errorResponse(e);
        }
    }
}
