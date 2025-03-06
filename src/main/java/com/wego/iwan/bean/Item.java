package com.wego.iwan.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Item {
    private String timestamp;

    @JsonProperty("carpark_data")
    private CarparkData[] carparkData;
}
