package com.wego.iwan.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CarparkInfo {

     @JsonProperty("total_lots")
    private String totalLots;

    @JsonProperty("lot_type")
    private String lotType;

    @JsonProperty("lots_available")
    private String lotsAvailable;
}
