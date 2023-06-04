package com.yunusakin.couriertracking.controller.data.response;

public class CreateCourierResponse {
    private String name ;
    private String token;


    public CreateCourierResponse() {
    }

    public CreateCourierResponse(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
