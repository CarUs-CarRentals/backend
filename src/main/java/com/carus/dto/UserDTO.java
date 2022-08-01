package com.carus.dto;

import com.carus.entities.UserEntity;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String login;
    private String email;

    public UserDTO(UserEntity entity) {
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.email = entity.getEmail();
    }

}
