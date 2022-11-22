package com.carus.repositories;

import com.carus.entities.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Long> {

    @Query(value = "select * from rental r where r.user_uuid = ?1", nativeQuery = true)
    List<RentalEntity> findRentalsByUserUuid(String userUuid);

    @Query(value = "select * from rental c where c.car_id = ?1", nativeQuery = true)
    List<RentalEntity> findRentalsByCarId(Long carId);
}
