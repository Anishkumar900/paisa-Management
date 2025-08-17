package com.paisaManagement.service;

import com.paisaManagement.model.User;
import com.paisaManagement.response.JWTResponse;
import com.paisaManagement.response.UserDTO;

public interface AuthService {
    User register(User user);
    void sendOTP(User user);
    boolean verifyEmail(User user);
    JWTResponse login(User user);
    void forgetPassword(User user);
    UserDTO jwtVerify(JWTResponse jwtResponse);
}
