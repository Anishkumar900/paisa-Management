package com.paisaManagement.controller;

import com.paisaManagement.cloudinary.CloudinaryService;
import com.paisaManagement.model.User;
import com.paisaManagement.response.JWTResponse;
import com.paisaManagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CloudinaryService cloudinaryService;

    @PostMapping("/register")
    public ResponseEntity<?> signIn(@RequestPart("user") User user,
                                    @RequestPart(value = "profileImage",required = false)MultipartFile profileImage){
//            System.out.println("test");
        try{
//            System.out.println(user);
            if (profileImage != null && !profileImage.isEmpty()) {
                String profileUrl = cloudinaryService.uploadFile(profileImage);
                user.setProfileImage(profileUrl);
            }
//            System.out.println(user);
            User saveUser = authService.register(user);

            return new ResponseEntity<>(saveUser,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOTP(@RequestBody User user){
        try {
            authService.sendOTP(user);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestBody User user){
        boolean result=authService.verifyEmail(user);
        try{
            if(result ){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            throw new RuntimeException("Wrong OTP");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            JWTResponse result =authService.login(user);
            return new ResponseEntity<>(result,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

