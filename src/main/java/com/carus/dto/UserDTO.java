package com.carus.dto;

import com.carus.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate memberSince;
    private String about;
    private String profileImageUrl;

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
        this.memberSince = entity.getMemberSince();
        this.about = entity.getAbout();
        this.profileImageUrl = entity.getProfileImageUrl();
    }

}
