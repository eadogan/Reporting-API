package com.rpdpymnt.reporting.repository;

import java.util.Optional;

import com.rpdpymnt.reporting.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByEmail(String email);
}