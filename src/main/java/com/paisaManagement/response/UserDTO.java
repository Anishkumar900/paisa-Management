package com.paisaManagement.response;

import com.paisaManagement.enumClass.ROLE;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private ROLE role;
    private String profileImage;
    private String deathOfBirth;
    private String phoneNumber;
    private boolean verified;
}
