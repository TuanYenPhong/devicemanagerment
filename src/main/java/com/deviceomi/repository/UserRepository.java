package com.deviceomi.repository;

import com.deviceomi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Transactional
    UserEntity findByUserName(String username);

    Boolean existsByUserName(String username);

    @Query("SELECT u.status from UserEntity u where u.userName=?1")
    Integer checkAccount(String username);

    List<UserEntity> findAllByStatus(int status);
}
