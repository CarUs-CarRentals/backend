package com.carus.dto;

import com.carus.entities.CarEntity;
import com.carus.enums.FuelEnum;
import com.carus.enums.GearEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CarDTO implements Serializable {

    private Long id;
    private String brand;

    private CarUserDTO user;
    private String model;
    private Integer year;
    private String color;
    private String plate;
    private FuelEnum fuel;
    private GearEnum gearShift;
    private Byte doors;
    private Integer seats;
    private Integer trunk;

    public CarDTO(CarEntity entity) {
        this.id = entity.getId();
        this.brand = entity.getBrand();
        this.model = entity.getModel();
        this.year = entity.getYear();
        this.color = entity.getColor();
        this.plate = entity.getPlate();
        this.fuel = entity.getFuel();
        this.gearShift = entity.getGearShift();
        this.doors = entity.getDoors();
        this.seats = entity.getSeats();
        this.trunk = entity.getTrunk();
    }

}
