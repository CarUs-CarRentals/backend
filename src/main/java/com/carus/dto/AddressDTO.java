package com.carus.dto;

import com.carus.entities.AddressEntity;
import com.carus.enums.EState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    private String cep;
    private EState state;
    private String city;
    private String neighborhood;
    private String street;
    private Integer number;

    public AddressDTO (AddressEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.cep = entity.getCep();
            this.state = entity.getState();
            this.city = entity.getCity();
            this.neighborhood = entity.getNeighborhood();
            this.street = entity.getStreet();
            this.number = entity.getNumber();
        }
    }
}
