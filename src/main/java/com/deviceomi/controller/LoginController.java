package com.deviceomi.controller;

import com.deviceomi.model.PasswordReset;
import com.deviceomi.payload.request.LoginRequest;
import com.deviceomi.payload.request.PasswordResetRequest;
import com.deviceomi.payload.request.NewPasswordRequest;
import com.deviceomi.payload.response.JwtResponse;
import com.deviceomi.payload.response.MessageResponse;
import com.deviceomi.service.EmailService;
import com.deviceomi.service.LoginService;
import com.deviceomi.service.PasswordResetTokenService;
import com.deviceomi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Api(value = "Login Auth APIs")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Đăng nhập bằng account")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@ApiParam("Thông tin account")  @Valid @RequestBody LoginRequest loginRequest) {
        try {
            JwtResponse jwtResponse=loginService.login(loginRequest);
            if (jwtResponse != null) return ResponseEntity.ok(jwtResponse);
            return ResponseEntity.ok(new MessageResponse("Username hoặc password không chính xác"));
        }catch (Exception e){
            return ResponseEntity.ok(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "send token to email reset password")
    @PostMapping("/password_reset")
    public ResponseEntity senMail(@ApiParam("Email rest password") @RequestBody PasswordResetRequest passwordResetRequest) throws MessagingException {
        try {
            PasswordReset passwordReset=passwordResetTokenService.createPasswordResetToken(passwordResetRequest);
            if (emailService.sendmail(passwordReset)) return new ResponseEntity(HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Reset Password account")
    @PostMapping("/password_reset/{token}")
    public ResponseEntity resetPasswrd(@PathVariable String token,@RequestBody NewPasswordRequest password){
        try {
            if(userService.resetPassword(token,password)) return new ResponseEntity(HttpStatus.OK);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
