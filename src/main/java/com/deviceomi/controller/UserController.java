package com.deviceomi.controller;

import com.deviceomi.payload.request.NewPasswordRequest;
import com.deviceomi.payload.request.SignupRequest;
import com.deviceomi.payload.response.MessageResponse;
import com.deviceomi.payload.response.UserResponse;
import com.deviceomi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
@Api(value = "User Auth APIs")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "Lấy thông tin User")
    @GetMapping("")
    public ResponseEntity<?> findAllUser(){
        try {
            List<UserResponse> users=userService.findAllUser();
            if (users.isEmpty()) return ResponseEntity.ok(HttpStatus.NO_CONTENT);
            return new ResponseEntity(users,HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Tạo mới user")
    @PostMapping("")
    public ResponseEntity<?> createUser(@ApiParam(value = "Tạo mới User") @Valid @RequestBody SignupRequest signUpRequest) {
        try {
            if(!userService.createUser(signUpRequest)) return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }catch (Exception e){
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Chỉnh sửa User")
    @PutMapping("")
    public ResponseEntity<?> updateUser(@ApiParam(value = "Chỉnh sửa User") @Valid @RequestBody SignupRequest signUpRequest) {
        try {
            if(userService.updateUser(signUpRequest)) return new ResponseEntity(HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Xóa user")
    @PutMapping("/{id}")
    public ResponseEntity<?> deleteUser(@ApiParam(value = "Xóa User") @PathVariable Long id){
        try {
            if(userService.deleteUser(id)) return new ResponseEntity(HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
