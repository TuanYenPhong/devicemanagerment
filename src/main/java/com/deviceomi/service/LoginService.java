package com.deviceomi.service;

import com.deviceomi.payload.request.LoginRequest;
import com.deviceomi.payload.response.JwtResponse;

public interface LoginService {
    JwtResponse login(LoginRequest loginRequest);
}
