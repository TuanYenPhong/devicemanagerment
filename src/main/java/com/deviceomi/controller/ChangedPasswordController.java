package com.deviceomi.controller;

import com.deviceomi.payload.request.NewPasswordRequest;
import com.deviceomi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@Api(value = "Changed Password APIs")
public class ChangedPasswordController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "User đổi mật khẩu")
    @PutMapping("/changed_password")
    public ResponseEntity changedPassword(@ApiParam("Nhập Password mới") @RequestBody NewPasswordRequest newPasswordRequest){
        try {
            if(userService.changedPassword(newPasswordRequest)) return new ResponseEntity(HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
