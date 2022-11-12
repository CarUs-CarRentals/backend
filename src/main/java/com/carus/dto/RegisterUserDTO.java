package com.carus.dto;

import com.carus.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {

    private String uuid;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public RegisterUserDTO(UserEntity userEntity) {
        this.uuid = userEntity.getUuid();
        this.login = userEntity.getLogin();
        this.password = userEntity.getPassword();
        this.email = userEntity.getEmail();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
    }

}
