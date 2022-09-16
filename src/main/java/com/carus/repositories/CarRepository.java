package com.carus.repositories;

import com.carus.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long>, QuerydslPredicateExecutor<CarEntity> {

//    final QCarEntity car = QCarEntity.carEntity;

//    default List<CarEntity> findCarsByUserId(Long userId) {
//        JPAQuery<CarEntity> query = new JPAQuery<>();
//        query.select(car).from(car)
//                .where(car.user.id.eq(userId));
//
//        return query.fetch();
//    }

    @Query("select c from CarEntity c where c.user.id = ?1")
    List<CarEntity> findCarsByUserId(Long userId);
}