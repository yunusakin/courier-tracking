package com.yunusakin.couriertracking.controller.data.request;

import javax.validation.constraints.NotBlank;

public class CreateCourierRequest {
    @NotBlank(message = "name is required.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
