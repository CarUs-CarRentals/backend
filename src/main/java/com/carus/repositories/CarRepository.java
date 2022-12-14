package com.carus.repositories;

import com.carus.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long>, QuerydslPredicateExecutor<CarEntity>, CarRepositoryCustom {

    @Query(value = "select * from car c where c.user_uuid = ?1", nativeQuery = true)
    List<CarEntity> findCarsByUserUuid(String userUuid);

    @Query(value = "update car set active = 0 where id = ?1", nativeQuery = true)
    void inactivateCar(Long id);
}