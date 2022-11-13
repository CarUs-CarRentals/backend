package com.carus.dto;

import com.carus.entities.RateCarEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateCarDTO {

    private Long id;
    private String userUuid;
    private Long carId;
    private Double rate;
    private String description;

    @JsonDeserialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    public RateCarDTO(RateCarEntity entity) {
        this.id = entity.getId();
        this.userUuid = entity.getUser().getUuid();
        this.carId = entity.getCar().getId();
        this.rate = entity.getRate();
        this.description = entity.getDescription();
        this.date = entity.getDate();
    }
}
