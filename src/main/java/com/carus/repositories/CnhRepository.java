package com.carus.repositories;

import com.carus.entities.CnhEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CnhRepository extends JpaRepository<CnhEntity, Long> {

    @Query(value = "select * from cnh where user_uuid = ?1", nativeQuery = true)
    Optional<CnhEntity> findByUserUuid(String userUuid);
}
