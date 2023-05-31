package com.yunusakin.couriertracking.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static ResponseEntity<Object> failHttpResponse(boolean isError,String errorCode, String errorMessage, HttpStatus status, Object responseObject) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success",isError);
        map.put("errorCode", errorCode);
        map.put("errorMessage", errorMessage);
        map.put("status", status.value());
        map.put("response", responseObject);

        return new ResponseEntity<Object>(map,status);
    }
    public static ResponseEntity<Object> successHttpResponse() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success",true);
        map.put("status", HttpStatus.OK.value());
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    public static ResponseEntity<Object> successHttpResponse(Object responseObject) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success",true);
        map.put("status", HttpStatus.OK.value());
        map.put("response", responseObject);
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    public static ResponseEntity<Object> errorResponse(Exception e){
        return ResponseUtil.failHttpResponse(false,null,e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
}
