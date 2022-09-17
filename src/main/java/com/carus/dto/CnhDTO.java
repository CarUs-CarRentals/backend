package com.carus.dto;

import com.carus.entities.CnhEntity;
import com.carus.entities.UserEntity;
import com.carus.enums.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnhDTO {

    private Long id;
    private UserEntity user;
    private String rg;
    private String registerNumber;
    private String cnhNumber;
    private String expirationDate;
    private String birthDate;
    private StateEnum stateEnum;

    public CnhDTO(CnhEntity entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.rg = entity.getRg();
        this.registerNumber = entity.getRegisterNumber();
        this.cnhNumber = entity.getCnhNumber();
        this.expirationDate = entity.getExpirationDate();
        this.birthDate = entity.getBirthDate();
        this.stateEnum = entity.getStateEnum();
    }

}
