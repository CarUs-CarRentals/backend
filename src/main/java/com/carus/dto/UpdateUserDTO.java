package com.carus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO implements Serializable {

    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String cpf;
    private String rg;
    private String phone;
    private String gender;
    private String password;
    private String about;
    private String profileImageUrl;
}
