package com.carus.repositories;

import com.carus.entities.RateCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateCarRepository extends JpaRepository<RateCarEntity, Long> {

    @Query("select r from RateCarEntity r where r.car.id = ?1")
    List<RateCarEntity> findAllByCarId(Long carId);

    @Query("select r from RateCarEntity r where r.user.uuid = ?1")
    List<RateCarEntity> findByUserUuid(String userUuid);
}

