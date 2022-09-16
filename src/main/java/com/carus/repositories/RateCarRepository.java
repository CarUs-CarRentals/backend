package com.carus.repositories;

import com.carus.entities.RateCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateCarRepository extends JpaRepository<RateCarEntity, Long> {}

