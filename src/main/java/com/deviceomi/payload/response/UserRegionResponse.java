package com.deviceomi.payload.response;

import com.deviceomi.model.UserEntity;
import lombok.Data;

@Data
public class UserRegionResponse {
    private Long id;

    private String name;

    private String region;

    public UserRegionResponse(UserEntity userEntity){
        setId(userEntity.getId());
        setName(userEntity.getFullname());
        setRegion("Nhân viên");
    }
}
