package com.carus.dto;

import com.carus.entities.RateUserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateUserDTO {

    private Long id;
    private String ratedUser;
    private String evaluatedUser;
    private Double rate;
    private String description;
    private Long rental;
    @JsonDeserialize
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    public RateUserDTO(RateUserEntity entity) {
        this.id = entity.getId();
        this.ratedUser = entity.getRatedUser().getUuid();
        this.evaluatedUser = entity.getEvaluatedUser().getUuid();
        this.rate = entity.getRate();
        this.description = entity.getDescription();
        this.rental = entity.getRental().getId();
        this.date = entity.getDate();
    }
}
