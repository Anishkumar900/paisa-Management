package com.paisaManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paisaManagement.enumClass.ROLE;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Expenses> expenses = new ArrayList<>();

    @OneToMany(mappedBy ="user" , cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Bank> bank=new ArrayList<>();

}
