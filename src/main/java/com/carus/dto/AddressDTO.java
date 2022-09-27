package com.carus.dto;

import com.carus.entities.AddressEntity;
import com.carus.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    private UserEntity user;
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private int number;

    public AddressDTO (AddressEntity entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.cep = entity.getCep();
        this.state = entity.getState();
        this.city = entity.getCity();
        this.neighborhood = entity.getNeighborhood();
        this.street = entity.getNeighborhood();
        this.number = entity.getNumber();
    }
}