package com.wego.iwan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "car_parks")
@Data
public class CarPark {

    @Id
    @Column(name = "car_park_no", length = 10, nullable = false)
    private String carParkNo;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "x_coord", precision = 10, scale = 4)
    private Double xCoord;

    @Column(name = "y_coord", precision = 10, scale = 4)
    private Double yCoord;

    @Column(name = "car_park_type", length = 50)
    private String carParkType;

    @Column(name = "type_of_parking_system", length = 50)
    private String typeOfParkingSystem;

    @Column(name = "short_term_parking", length = 50)
    private String shortTermParking;

    @Column(name = "free_parking", length = 50)
    private String freeParking;

    @Column(name = "night_parking", length = 3)
    private String nightParking;

    @Column(name = "car_park_decks")
    private Integer carParkDecks;

    @Column(name = "gantry_height", precision = 3, scale = 1)
    private Double gantryHeight;

    @Column(name = "car_park_basement", length = 1)
    private String carParkBasement;
}
