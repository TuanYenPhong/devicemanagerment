package com.deviceomi.service.impl;

import com.deviceomi.model.PasswordReset;
import com.deviceomi.model.UserEntity;
import com.deviceomi.payload.request.PasswordResetRequest;
import com.deviceomi.repository.PasswordResetRepository;
import com.deviceomi.repository.UserRepository;
import com.deviceomi.service.PasswordResetTokenService;
import com.deviceomi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    @Value("${app.reset.password.expiration}")
    private Long expiration;

    @Autowired
    private PasswordResetRepository passwordResetRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PasswordReset createPasswordResetToken(String email){
        if(!userRepository.existsByUserName(email)) return null;
        Util util=new Util();
        UserEntity user=userRepository.findByUserName(email);
        if (user==null) return null;
        PasswordReset passwordReset=passwordResetRepository.findByUser(user).orElse(new PasswordReset().setUser(user));
        passwordReset.setToken(util.generateRandomUuid());
        passwordReset.setExprixyDate(Instant.now().plusMillis(expiration));
        passwordResetRepository.save(passwordReset);
        return passwordReset;
    }

}
