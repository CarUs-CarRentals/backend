package com.carus.dto;

import com.carus.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserCompleteDTO extends UserDTO {

    private CnhDTO cnh;

    public UserCompleteDTO(UserEntity entity, CnhDTO cnh) {
        super(entity);
        this.cnh = cnh;
    }
}
