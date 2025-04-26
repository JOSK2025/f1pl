package com.josk.f1pp.repository;

import com.josk.f1pp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findUserByEmail(String email);
    UserEntity findByEmail(String email);
}