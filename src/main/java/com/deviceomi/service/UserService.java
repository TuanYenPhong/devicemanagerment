package com.deviceomi.service;

import com.deviceomi.payload.request.NewPasswordRequest;
import com.deviceomi.payload.request.SignupRequest;
import com.deviceomi.payload.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAllUser();
    boolean createUser(SignupRequest signUpRequest);
    boolean deleteUser(Long id);
    boolean updateUser(SignupRequest signupRequest);
    boolean changedPassword(NewPasswordRequest newPasswordRequest);
    boolean resetPassword(String token,NewPasswordRequest newPasswordRequest);
}
