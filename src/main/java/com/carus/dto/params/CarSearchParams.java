package com.carus.dto.params;

import com.carus.enums.ECategory;
import com.carus.enums.EGear;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarSearchParams implements Serializable {

    private EGear gearShift;
    private ECategory category;
    private String address;
    private String brand;
    private String model;
    private Integer year;
    private Integer seats;

}