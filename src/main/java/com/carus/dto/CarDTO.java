package com.carus.dto;

import com.carus.entities.CarEntity;
import com.carus.enums.CategoryEnum;
import com.carus.enums.FuelEnum;
import com.carus.enums.GearEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDTO {

    private Long id;
    private String brand;

    private CarUserDTO user;
    private String model;
    private Integer year;
    private String color;
    private String plate;
    private FuelEnum fuel;
    private GearEnum gearShift;
    private CategoryEnum category;
    private Byte doors;
    private Integer seats;
    private String pickupLocation;
    private String returnLocation;

    public CarDTO(CarEntity entity) {
        this.id = entity.getId();
        this.user = new CarUserDTO(entity.getUser().getId());
        this.brand = entity.getBrand();
        this.model = entity.getModel();
        this.year = entity.getYear();
        this.plate = entity.getPlate();
        this.fuel = entity.getFuel();
        this.gearShift = entity.getGearShift();
        this.category = entity.getCategory();
        this.doors = entity.getDoors();
        this.seats = entity.getSeats();
        this.pickupLocation = entity.getPickupLocation();
        this.returnLocation = entity.getReturnLocation();
    }

}
