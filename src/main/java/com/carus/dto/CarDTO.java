package com.carus.dto;

import com.carus.entities.CarEntity;
import com.carus.entities.ImageEntity;
import com.carus.enums.ECategory;
import com.carus.enums.EFuel;
import com.carus.enums.EGear;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
    private Long latitude;
    private Long longitude;
    private String description;
    private String address;

    private List<ImageEntity> carImages = new ArrayList<>(0);

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
    }

}
