package com.deviceomi.repository;

import com.deviceomi.model.ERole;
import com.deviceomi.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(ERole eRole);
}
