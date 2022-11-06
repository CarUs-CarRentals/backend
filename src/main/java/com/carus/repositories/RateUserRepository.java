package com.carus.repositories;

import com.carus.entities.RateUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateUserRepository extends JpaRepository<RateUserEntity, Long> {

    @Query("select count(r) from RateUserEntity r where r.ratedUser.id = ?1")
    Long countRatesByUserId(Long userId);
}

