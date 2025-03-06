package com.wego.iwan.bean;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.PropertyUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wego.iwan.model.ParkInfo;

import lombok.Data;


@Data
public class ParkAvail {

    private String address;

    private Double latitude;

    private Double longitude;

    @JsonProperty("total_lots")
    private Integer totalLots;

    @JsonProperty("available_lots")
    private Integer lotsAvailable;

    public ParkAvail(ParkInfo parkInfo){
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                PropertyUtils.setProperty(this, field.getName(), PropertyUtils.getProperty(parkInfo, field.getName()));
            }
        } catch (Exception e) {
        }
    }


}
