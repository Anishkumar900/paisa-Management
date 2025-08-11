package com.paisaManagement.serviceImpl;

import com.paisaManagement.configuration.JWTService;
import com.paisaManagement.enumClass.ROLE;
import com.paisaManagement.exception.UserAlreadyExistException;
import com.paisaManagement.mailSender.OTPRegisterSend;
import com.paisaManagement.model.User;
import com.paisaManagement.repository.UserRepository;
import com.paisaManagement.response.JWTResponse;
import com.paisaManagement.service.AuthService;
import com.paisaManagement.util.OtpGenerate;
import com.paisaManagement.util.UpdateNameEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final OTPRegisterSend otpRegisterSend;
    private final JWTService jwtService;

    @Override
    public User register(User user) {
        User existingUser=userRepository.findByEmail(user.getEmail());
        if (existingUser !=null && existingUser.isVerified()) {

                throw new UserAlreadyExistException("User with this email already exists");

        }
        if (existingUser != null && !existingUser.isVerified()) {
            userRepository.deleteAllByEmail(existingUser.getEmail());
        }

        user.setName(UpdateNameEmail.updateName(user.getName()));
        user.setEmail(UpdateNameEmail.updateEmail(user.getEmail()));
        String otp = OtpGenerate.generateOTP(4);
        user.setOtp(otp);
        user.setRole(ROLE.USER);
        user.setOtpValidityTime(LocalDateTime.now().plusMinutes(20));
        user.setCreatedTime(LocalDateTime.now());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        String subject = "Verify your Wealth Care account";
        String heading = "Welcome to Wealth Care! Verify your Email";
        otpRegisterSend.sendEmail(user.getEmail(), subject, heading, otp);
        userRepository.save(user);
        return user;
    }

    @Override
    public void sendOTP(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new RuntimeException("User not found with email: " + user.getEmail());
        }
        String otp = OtpGenerate.generateOTP(4);
        existingUser.setOtp(otp);
        existingUser.setOtpValidityTime(LocalDateTime.now().plusMinutes(20));
        String subject = "Verify your Wealth Care account";
        String heading = "Welcome to Wealth Care! Verify your Email";
        otpRegisterSend.sendEmail(user.getEmail(), subject, heading, otp);
        userRepository.save(existingUser);

    }

    @Override
    public boolean verifyEmail(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser==null){
            throw new RuntimeException("User not found with email: " + user.getEmail());
        }
        try{
            if (Objects.equals(user.getOtp(), existingUser.getOtp())
                    && LocalDateTime.now().isBefore(existingUser.getOtpValidityTime())) {
                existingUser.setVerified(true);
                userRepository.save(existingUser);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JWTResponse login(User user) {
        String email=UpdateNameEmail.updateEmail(user.getEmail());
        User exitingUser=userRepository.findByEmail(email);
        if (exitingUser == null ) {
            throw new RuntimeException("User not found");
        }
        if (!exitingUser.isVerified()) {
            throw new RuntimeException("User not verified");
        }

        if (!bCryptPasswordEncoder.matches(user.getPassword(), exitingUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token=jwtService.generateJWTToken(user.getEmail());
        JWTResponse jwtResponse=new JWTResponse();
        jwtResponse.setToken(token);

        return jwtResponse;
    }
}
