package com.paisaManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.paisaManagement.enumClass.ROLE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private ROLE role;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String profileImage;
    private String deathOfBirth;
    private String phoneNumber;
    private boolean verified=false;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String otp;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime otpValidityTime;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdTime;
}
