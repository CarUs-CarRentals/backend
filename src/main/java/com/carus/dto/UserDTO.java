package com.carus.dto;

import com.carus.entities.UserEntity;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String email;

    public UserDTO(UserEntity entity) {
        this.id = entity.getId();
        this.username = entity.getLogin();
        this.email = entity.getEmail();
    }

}
