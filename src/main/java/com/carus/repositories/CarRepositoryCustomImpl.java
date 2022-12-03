package com.carus.repositories;

import com.carus.dto.CarDTO;
import com.carus.dto.params.CarSearchParams;
import com.carus.entities.QCarEntity;
import com.carus.entities.QRateCarEntity;
import com.carus.entities.QRentalEntity;
import com.carus.enums.ECategory;
import com.carus.enums.EGear;
import com.querydsl.core.types.Projections;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class CarRepositoryCustomImpl implements CarRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    private final QCarEntity carEntity = QCarEntity.carEntity;
    private final QRateCarEntity rateCarEntity = QRateCarEntity.rateCarEntity;

    private final QRentalEntity rentalEntity = QRentalEntity.rentalEntity;

    @Override
    public List<CarDTO> filterCars(CarSearchParams searchParams) {
        JPAQuery<CarDTO> query = new JPAQuery<>(entityManager);
        query.select(
                Projections.bean(CarDTO.class),
                carEntity.id,
                carEntity.brand,
                carEntity.model,
                carEntity.category,
                carEntity.description,
                carEntity.fuel,
                carEntity.doors,
                carEntity.gearShift,
                carEntity.trunk,
                carEntity.plate,
                carEntity.year,
                carEntity.seats,
                carEntity.price,
                carEntity.latitude,
                carEntity.longitude,
                carEntity.address,
                carEntity.user,
                carEntity.active,
                carEntity.carImages,
                rentalEntity.count().as("qtCarRentals"),
                rateCarEntity.rate.avg().as("rateCarAverage")
        );

        query.from(carEntity);
        query.leftJoin(rentalEntity).on(rentalEntity.car.id.eq(carEntity.id));
        query.leftJoin(rateCarEntity).on(rateCarEntity.car.id.eq(carEntity.id));
        whereYearIs(query, searchParams.getYear());
        whereAddressLike(query, searchParams.getAddress());
        whereBrandLike(query, searchParams.getBrand());
        whereCategoryIs(query, searchParams.getCategory());
        whereModelLike(query, searchParams.getModel());
        whereGearShiftIs(query, searchParams.getGearShift());
        whereSeatsNumberIs(query, searchParams.getSeats());
        query.where(carEntity.active.eq(true));

        return query.fetch();
    }

    // Filters
    private void whereGearShiftIs(JPAQuery<CarDTO> query, EGear gear) {
        if (gear != null) query.where(carEntity.gearShift.eq(gear));
    }

    private void whereCategoryIs(JPAQuery<CarDTO> query, ECategory category) {
        if (category != null) query.where(carEntity.category.eq(category));
    }

    private void whereAddressLike(JPAQuery<CarDTO> query, String address) {
        if (!StringUtils.isNullOrEmpty(address)) query.where(carEntity.address.like("%" + address + "%"));
    }

    private void whereBrandLike(JPAQuery<CarDTO> query, String brand) {
        if (!StringUtils.isNullOrEmpty(brand)) query.where(carEntity.brand.like("%" + brand + "%"));
    }

    private void whereModelLike(JPAQuery<CarDTO> query, String model) {
        if (!StringUtils.isNullOrEmpty(model)) query.where(carEntity.model.like("%" + model + "%"));
    }

    private void whereYearIs(JPAQuery<CarDTO> query, Integer year) {
        if (year != null) query.where(carEntity.year.eq(year));
    }

    private void whereSeatsNumberIs(JPAQuery<CarDTO> query, Integer seats) {
        if (seats != null) query.where(carEntity.seats.eq(seats));
    }

    @Override
    public Double rateCarAverage(Long carId) {
        JPAQuery<Double> query = new JPAQuery<>(entityManager);
        query.select(rateCarEntity.rate.avg()).from(rateCarEntity);

        query.where(rateCarEntity.car.id.eq(carId));
        return query.fetchFirst();
    }

    @Override
    public Integer qtCarRentals(Long carId) {
        JPAQuery<Integer> query = new JPAQuery<>(entityManager);
        query.select(rentalEntity.count());
        query.from(rentalEntity);
        query.where(rentalEntity.car.id.eq(carId));

        return query.fetchOne();
    }
}
