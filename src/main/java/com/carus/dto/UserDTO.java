package com.carus.dto;

import com.carus.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String cpf;
    private String rg;
    private String phone;
    private String gender;


    public UserDTO(UserEntity entity) {
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.email = entity.getEmail();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.cpf = entity.getCpf();
        this.rg = entity.getRg();
        this.phone = entity.getPhone();
        this.gender = entity.getGender();
    }

}
