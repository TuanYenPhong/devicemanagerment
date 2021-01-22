package com.deviceomi.service.impl;

import com.deviceomi.payload.request.LoginRequest;
import com.deviceomi.payload.response.JwtResponse;
import com.deviceomi.repository.RoleRepository;
import com.deviceomi.repository.UserRepository;
import com.deviceomi.security.JwtUtils;
import com.deviceomi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        if(!userRepository.existsByUserName(loginRequest.getUsername())||
                userRepository.checkAccount(loginRequest.getUsername()) != 1) return null;

        /**Xác thực thông tin username, pass
         * */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        /**Lấy thông tin vào security
         * */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /**Tạo jwt
         * */
        String jwt = jwtUtils.createJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
    }
}
