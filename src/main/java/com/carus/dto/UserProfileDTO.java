package com.carus.dto;

import com.carus.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO implements Serializable {

    private String firstName;
    private String lastName;
    private LocalDate memberSince;
    private String about;
    private Long rateNumber = 0L;
    private AddressDTO address;
    private CnhDTO cnh;
    private String profileImageUrl;
    private String cpf;
    private String phone;
    private EGender gender;
    private String email;

    public UserProfileDTO(UserDTO user, Long rateNumber, CnhDTO cnh) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.memberSince = user.getMemberSince();
        this.about = user.getAbout();
        this.address = user.getAddress();
        this.rateNumber = rateNumber;
        this.profileImageUrl = user.getProfileImageUrl();
        this.cnh = cnh;
        this.cpf = user.getCpf();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.email = user.getEmail();
    }
}
