package com.deviceomi.payload.response;

import com.deviceomi.model.UserEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserResponse {
    @NotBlank
    private String username;

    @NotBlank
    private String fullname;

    @NotBlank
    private String userDepartment;

    @NotBlank
    private Set<String> roles;

    @NotBlank
    private String createBy;

    @NotBlank
    private Date createDate;

    public UserResponse(UserEntity userEntity){
        Set<String> strRole=new HashSet<>();
        userEntity.getRoles().stream().map(role->role.getName().name()).forEach(strRole::add);
        this.setUsername(userEntity.getUserName());
        this.setFullname(userEntity.getFullname());
        if(userEntity.getUserDepartment()!=null)
            this.setUserDepartment(userEntity.getUserDepartment().getNameDepartment());
        this.setRoles(strRole);
        if(userEntity.getCreatedBy()!=null) this.setCreateBy(userEntity.getCreatedBy());
        this.setCreateDate(userEntity.getCreatedDate());
    }
}
