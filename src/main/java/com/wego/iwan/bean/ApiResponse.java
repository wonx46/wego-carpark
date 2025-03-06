package com.wego.iwan.bean;

import lombok.Data;

@Data
public class ApiResponse {
    private ApiInfo api_info;
    private Item[] items;
}
