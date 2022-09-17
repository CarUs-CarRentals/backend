package com.carus.repositories;

import com.carus.entities.CnhEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnhRepository extends JpaRepository<CnhEntity, Long> {}
