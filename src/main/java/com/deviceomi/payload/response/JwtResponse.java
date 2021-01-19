package com.deviceomi.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class JwtResponse {
    private final String token;
    private String type = "Bearer";
    private final Long id;
    private final String username;
    private final List<String> roles;


//    public JwtResponse(String token, Long id, String username, List<String> roles) {
//        this.token = token;
//        this.id = id;
//        this.username = username;
//        this.roles = roles;
//    }
}
