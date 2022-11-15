package com.carus.repositories;

import com.carus.entities.RateUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateUserRepository extends JpaRepository<RateUserEntity, Long> {

    @Query("select count(r) from RateUserEntity r where r.ratedUser.uuid = ?1")
    Long countRatesByUserId(String userUuid);

    @Query("select r from RateUserEntity r where r.evaluatedUser.uuid = ?1")
    List<RateUserEntity> findByUserUuid(String userUuid);
}

