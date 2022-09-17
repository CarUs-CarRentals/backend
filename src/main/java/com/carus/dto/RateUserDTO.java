package com.carus.dto;

import com.carus.entities.RateUserEntity;
import com.carus.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateUserDTO {

    private Long id;
    private UserEntity ratedUser;
    private UserEntity evaluatedUser;
    private Double rate;
    private String description;
    private String date;

    public RateUserDTO(RateUserEntity entity) {
        this.id = entity.getId();
        this.ratedUser = entity.getRatedUser();
        this.evaluatedUser = entity.getEvaluatorUser();
        this.rate = entity.getRate();
        this.description = entity.getDescription();
        this.date = entity.getDate();
    }
}
