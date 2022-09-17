package com.carus.repositories;

import com.carus.entities.RateUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateUserRepository extends JpaRepository<RateUserEntity, Long> {}

