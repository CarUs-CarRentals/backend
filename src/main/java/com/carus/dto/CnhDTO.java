package com.carus.dto;

import com.carus.entities.CnhEntity;
import com.carus.enums.EState;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnhDTO {

    private Long id;
    private String userId;
    private String rg;
    private String registerNumber;
    private String cnhNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private EState state;

    public CnhDTO(CnhEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getUuid();
        this.rg = entity.getRg();
        this.registerNumber = entity.getRegisterNumber();
        this.cnhNumber = entity.getCnhNumber();
        this.expirationDate = entity.getExpirationDate();
        this.birthDate = entity.getBirthDate();
        this.state = entity.getState();
    }

}
