package com.carus.dto;

import com.carus.entities.CarEntity;
import com.carus.entities.ImageEntity;
import com.carus.enums.ECategory;
import com.carus.enums.EFuel;
import com.carus.enums.EGear;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CarDTO implements Serializable {

    private Long id;
    private String brand;
    private String user;
    private String model;
    private Integer year;
    private String plate;
    private EFuel fuel;
    private EGear gearShift;
    private ECategory category;
    private Integer doors;
    private Integer seats;
    private Integer trunk;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String description;
    private String address;

    private List<ImageEntity> carImages = new ArrayList<>(0);
    private Double price;
    private Boolean active = true;
    private Double rateCarAverage = 0.0;
    private Long qtCarRentals = 0L;

    public CarDTO(CarEntity entity) {
        this.id = entity.getId();
        this.user = entity.getUser().getUuid();
        this.brand = entity.getBrand();
        this.model = entity.getModel();
        this.year = entity.getYear();
        this.plate = entity.getPlate();
        this.fuel = entity.getFuel();
        this.gearShift = entity.getGearShift();
        this.category = entity.getCategory();
        this.doors = entity.getDoors();
        this.seats = entity.getSeats();
        this.trunk = entity.getTrunk();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.description = entity.getDescription();
        this.address = entity.getAddress();
        this.carImages = entity.getCarImages();
        this.price = entity.getPrice();
        this.active = entity.getActive();
    }

    public CarDTO(CarEntity entity, Double rateCarAverage, Long qtCarRentals) {
        this.id = entity.getId();
        this.user = entity.getUser().getUuid();
        this.brand = entity.getBrand();
        this.model = entity.getModel();
        this.year = entity.getYear();
        this.plate = entity.getPlate();
        this.fuel = entity.getFuel();
        this.gearShift = entity.getGearShift();
        this.category = entity.getCategory();
        this.doors = entity.getDoors();
        this.seats = entity.getSeats();
        this.trunk = entity.getTrunk();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.description = entity.getDescription();
        this.address = entity.getAddress();
        this.carImages = entity.getCarImages();
        this.price = entity.getPrice();
        this.active = entity.getActive();
        if (rateCarAverage != null) this.rateCarAverage = BigDecimal.valueOf(rateCarAverage).setScale(1, RoundingMode.HALF_UP).doubleValue();
        if (qtCarRentals != null) this.qtCarRentals = qtCarRentals;
    }

}
