package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM UserEntity s " +
            "WHERE s.email = ?1"
    )
    Boolean selectExistsEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
