package com.deviceomi.payload.request;

import com.deviceomi.model.DepartmentEntity;
import com.deviceomi.model.UserEntity;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String fullname;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private String nameDepartment;

    @NotBlank
    private int satus;

    public UserEntity toUserEntity(PasswordEncoder encoder, DepartmentEntity departmentEntity){
        UserEntity userEntity=new UserEntity();
        userEntity.setUserName(this.username);
        userEntity.setFullname(this.fullname);
        userEntity.setPassword(encoder.encode(this.password));
        userEntity.setStatus(1);
        userEntity.setUserDepartment(departmentEntity);
        return userEntity;
    }

    public UserEntity toUserEntity(PasswordEncoder encoder, DepartmentEntity departmentEntity,UserEntity userEntity){
        userEntity.setFullname(this.getFullname());
        if(this.getPassword()!=null) userEntity.setPassword(encoder.encode(this.password));
        if(departmentEntity!=null) userEntity.setUserDepartment(departmentEntity);
        return userEntity;
    }
}
