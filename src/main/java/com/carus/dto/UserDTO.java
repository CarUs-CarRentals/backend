package com.carus.dto;

import com.carus.entities.UserEntity;
import com.carus.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String uuid;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String cpf;
    private String phone;
    private EGender gender;
    private LocalDate memberSince;
    private String about;
    private String profileImageUrl;

    public UserDTO(UserEntity entity) {
        this.uuid = entity.getUuid();
        this.login = entity.getLogin();
        this.email = entity.getEmail();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.cpf = entity.getCpf();
        this.phone = entity.getPhone();
        this.gender = entity.getGender();
        this.memberSince = entity.getMemberSince();
        this.about = entity.getAbout();
        this.profileImageUrl = entity.getProfileImageUrl();
    }

}
