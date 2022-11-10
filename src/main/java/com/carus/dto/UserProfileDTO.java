package com.carus.dto;

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

    public UserProfileDTO(UserDTO user, AddressDTO address, Long rateNumber) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.memberSince = user.getMemberSince();
        this.about = user.getAbout();
        this.address = address;
        this.rateNumber = rateNumber;
    }
}
