package com.carus.repositories;

import com.carus.dto.params.CarSearchParams;
import com.carus.entities.CarEntity;

import java.math.BigDecimal;
import java.util.List;

public interface CarRepositoryCustom {

    List<CarEntity> filterCars(CarSearchParams searchParams);

    Double rateCarAverage(Long id);
}
