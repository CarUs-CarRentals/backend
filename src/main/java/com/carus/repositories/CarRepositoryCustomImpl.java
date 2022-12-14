package com.carus.repositories;

import com.carus.dto.params.CarSearchParams;
import com.carus.entities.CarEntity;
import com.carus.entities.QCarEntity;
import com.carus.entities.QRateCarEntity;
import com.carus.entities.QRentalEntity;
import com.carus.enums.ECategory;
import com.carus.enums.EGear;
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
    public List<CarEntity> filterCars(CarSearchParams searchParams) {
        JPAQuery<CarEntity> query = new JPAQuery<>(entityManager);
        query.select(carEntity);
        query.from(carEntity);
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
    private void whereGearShiftIs(JPAQuery<CarEntity> query, EGear gear) {
        if (gear != null) query.where(carEntity.gearShift.eq(gear));
    }

    private void whereCategoryIs(JPAQuery<CarEntity> query, ECategory category) {
        if (category != null) query.where(carEntity.category.eq(category));
    }

    private void whereAddressLike(JPAQuery<CarEntity> query, String address) {
        if (!StringUtils.isNullOrEmpty(address)) query.where(carEntity.address.like("%" + address + "%"));
    }

    private void whereBrandLike(JPAQuery<CarEntity> query, String brand) {
        if (!StringUtils.isNullOrEmpty(brand)) query.where(carEntity.brand.like("%" + brand + "%"));
    }

    private void whereModelLike(JPAQuery<CarEntity> query, String model) {
        if (!StringUtils.isNullOrEmpty(model)) query.where(carEntity.model.like("%" + model + "%"));
    }

    private void whereYearIs(JPAQuery<CarEntity> query, Integer year) {
        if (year != null) query.where(carEntity.year.eq(year));
    }

    private void whereSeatsNumberIs(JPAQuery<CarEntity> query, Integer seats) {
        if (seats != null) query.where(carEntity.seats.eq(seats));
    }

    @Override
    public Double rateCarAverage(Long carId) {
        JPAQuery<Double> query = new JPAQuery<>(entityManager);
        query.select(rateCarEntity.rate.avg()).from(rateCarEntity);

        query.where(rateCarEntity.car.id.eq(carId));
        return query.fetchFirst();
    }
}
