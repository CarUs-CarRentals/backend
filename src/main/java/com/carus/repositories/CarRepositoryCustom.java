package com.carus.repositories;

import com.carus.dto.CarDTO;
import com.carus.dto.params.CarSearchParams;

import java.util.List;

public interface CarRepositoryCustom {

    List<CarDTO> filterCars(CarSearchParams searchParams);

    Double rateCarAverage(Long carId);

    Integer qtCarRentals(Long carId);
}
