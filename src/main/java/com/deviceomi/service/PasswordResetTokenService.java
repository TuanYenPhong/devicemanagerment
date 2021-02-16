package com.deviceomi.service;

import com.deviceomi.model.PasswordReset;
import com.deviceomi.payload.request.PasswordResetRequest;

public interface PasswordResetTokenService {
    PasswordReset createPasswordResetToken(String email);
}
