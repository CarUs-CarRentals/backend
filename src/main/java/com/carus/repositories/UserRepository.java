package com.carus.repositories;

import com.carus.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByLogin(String username);

    Optional<UserEntity> findByUuid(String uuid);

    void deleteByUuid(String uuid);
}
