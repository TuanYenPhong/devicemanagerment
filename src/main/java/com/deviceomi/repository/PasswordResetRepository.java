package com.deviceomi.repository;

import com.deviceomi.model.PasswordReset;
import com.deviceomi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetRepository extends JpaRepository<PasswordReset,Long> {
    PasswordReset findByToken(String token);

    Optional<PasswordReset> findByUser(UserEntity user);
}
