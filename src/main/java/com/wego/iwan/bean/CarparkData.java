package com.wego.iwan.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CarparkData {

    @JsonProperty("carpark_number")
    private String carparkNumber;

    @JsonProperty("update_datetime")
    private String updateDatetime;

    @JsonProperty("carpark_info")
    private CarparkInfo[] carparkInfo;

}
