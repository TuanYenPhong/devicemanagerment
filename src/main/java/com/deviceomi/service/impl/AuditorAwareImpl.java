package com.deviceomi.service.impl;

import com.deviceomi.model.UserEntity;
import com.deviceomi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<UserEntity> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserEntity> getCurrentAuditor() {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity=userRepository.findByUserName(username);
        return Optional.ofNullable(userEntity);
    }

}
