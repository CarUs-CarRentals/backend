package com.carus.dto;

import com.carus.entities.CarEntity;
import com.carus.enums.ECategory;
import com.carus.enums.EGear;
import com.carus.enums.EFuel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CarDTO implements Serializable {

    private Long id;
    private String brand;
    private Long user;
    private String model;
    private Integer year;
    private String plate;
    private EFuel fuel;
    private EGear gearShift;
    private ECategory category;
    private Integer doors;
    private Integer seats;
    private Integer trunk;
    private String pickupLocation;
    private String returnLocation;

    public CarDTO(CarEntity entity) {
        this.id = entity.getId();
        this.user = entity.getUser().getId();
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
        this.pickupLocation = entity.getPickupLocation();
        this.returnLocation = entity.getReturnLocation();
    }

}
