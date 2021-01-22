package com.deviceomi.service.impl;

import com.deviceomi.model.*;
import com.deviceomi.payload.request.NewPasswordRequest;
import com.deviceomi.payload.request.SignupRequest;
import com.deviceomi.payload.response.UserResponse;
import com.deviceomi.repository.*;
import com.deviceomi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private PasswordResetRepository passwordResetRepository;

    public HistoryEntity historyEntity(String context, String editObject){
        HistoryEntity historyEntity=new HistoryEntity();
        historyEntity.setContent(context);
        historyEntity.setPage("Quản lý hệ thống");
        historyEntity.setEditObject(editObject);
        return historyEntity;
    }

    @Override
    public List<UserResponse> findAllUser() {
        List<UserEntity> userEntities=userRepository.findAllByStatus(1);
        List<UserResponse> users=new ArrayList<>();
        userEntities.stream().map(u->new UserResponse(u)).forEach(users::add);
        return users;
    }

    @Override
    public boolean createUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUsername())||signUpRequest==null) return false;
        DepartmentEntity departmentEntity=departmentRepository.findByNameDepartment(signUpRequest.getNameDepartment());
        UserEntity user = signUpRequest.toUserEntity(encoder,departmentEntity);
        user.setRoles(roles(signUpRequest.getRole()));
        userRepository.save(user);
        historyRepository.save(historyEntity("đã tạo mới thông tin user với username ",user.getUserName()));
        return true;
    }

    @Override
    public boolean deleteUser(Long id) {
        UserEntity userEntity=userRepository.findById(id).orElse(new UserEntity());
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        if(userEntity!=null && userEntity.getStatus()==1 && (!username.equals(userEntity.getUserName()))) {
            userEntity.setStatus(0);
            userRepository.save(userEntity);
            historyRepository.save(historyEntity("đã xóa thông tin user với username ",userEntity.getUserName()));
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(SignupRequest signUpRequest) {
        UserEntity userEntity=userRepository.findByUserName(signUpRequest.getUsername());
        if (userEntity==null||userEntity.getStatus()!=1) return false;
        DepartmentEntity departmentEntity=departmentRepository.findByNameDepartment(signUpRequest.getNameDepartment());
        UserEntity user=userRepository.save(signUpRequest.toUserEntity(encoder,departmentEntity,userEntity));
        user.setRoles(roles(signUpRequest.getRole()));
        userRepository.save(user);
        historyRepository.save(historyEntity("đã chỉnh sửa thông tin user với username ",userEntity.getUserName()));
        return true;
    }

    /**
     * User changed password
     * @param password
     * @return boolean
     */
    @Override
    public boolean changedPassword(NewPasswordRequest password) {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user=userRepository.findByUserName(username);
        if(user==null||user.getStatus()!=1) return false;
        user.setPassword(encoder.encode(password.getPassword().strip()));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean resetPassword(String token,NewPasswordRequest newPasswordRequest) {
        PasswordReset passwordReset=passwordResetRepository.findByToken(token);
        if(passwordReset==null||verifyExpiration(passwordReset)||passwordReset.getUser().getStatus()!=1) return false;
        UserEntity userEntity=passwordReset.getUser();
        userEntity.setPassword(encoder.encode(newPasswordRequest.getPassword()));
        userRepository.save(userEntity);
        passwordReset.setExprixyDate(Instant.now().plusMillis(0));
        passwordResetRepository.save(passwordReset);
        return true;
    }

    public Set<RoleEntity> roles(Set<String> strRoles){
        Set<RoleEntity> roles = new HashSet<>();
        if (strRoles.isEmpty()) {
            RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        return roles;
    }

    public boolean verifyExpiration(PasswordReset passwordReset){
        if(passwordReset.getExprixyDate().compareTo(Instant.now())<0) return true;
        return false;
    }



}
