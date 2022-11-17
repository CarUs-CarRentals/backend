package com.carus.repositories;

import com.carus.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    @Query(value = "delete from image i inner join car_image ci on ci.image_id = i.id where ci.car_id = ?1", nativeQuery = true)
    void deleteAllByCarId(Long carId);
}
