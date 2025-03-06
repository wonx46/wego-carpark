package com.wego.iwan.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@NamedQuery(name="ParkInfo.findAll", query="SELECT v FROM ParkInfo v")
@Table(name="park_info")
@Data
public class ParkInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carpark_number", length = 10, nullable = false)
    private String carparkNumber;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "total_lots", nullable = false)
    private Integer totalLots;

    @Column(name = "lots_available", nullable = false)
    private Integer lotsAvailable;

    @Column(name = "lot_type", length = 50, nullable = false)
    private String lotType;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "update_datetime", nullable = false)
    private LocalDateTime updateDatetime;

    public ParkInfo(){
    }

    public ParkInfo(String carparkNumber, String address, Integer totalLots, Integer lotsAvailable, String lotType, Double latitude, Double longitude, LocalDateTime updateDatetime) {
        this.carparkNumber = carparkNumber;
        this.address = address;
        this.totalLots = totalLots;
        this.lotsAvailable = lotsAvailable;
        this.lotType = lotType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.updateDatetime = updateDatetime;
    }
}
