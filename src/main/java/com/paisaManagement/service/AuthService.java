package com.paisaManagement.service;

import com.paisaManagement.model.User;
import com.paisaManagement.response.JWTResponse;

public interface AuthService {
    User register(User user);
    void sendOTP(User user);
    boolean verifyEmail(User user);
    JWTResponse login(User user);
}
