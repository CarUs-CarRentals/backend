package com.carus.repositories;

import com.carus.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query(value = "select * from address where user_id = ?1 limit 1", nativeQuery = true)
    AddressEntity findAddressByUserId(Long userId);
}
