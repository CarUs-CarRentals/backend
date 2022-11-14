package com.carus.dto;

import com.carus.entities.CarEntity;
import com.carus.entities.RentalEntity;
import com.carus.entities.RateCarEntity;
import com.carus.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateCarDTO {

    private Long id;
    private UserEntity user;
    private CarEntity car;
    private RentalEntity location;
    private Double rate;
    private String description;
    private String date;

    public RateCarDTO(RateCarEntity entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.car = entity.getCar();
        this.location = entity.getLocation();
        this.rate = entity.getRate();
        this.description = entity.getDescription();
        this.date = entity.getDate();
    }
}
