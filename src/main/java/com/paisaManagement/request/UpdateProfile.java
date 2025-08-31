package com.paisaManagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfile {
    private String email;
    private String name;
    private String deathOfBirth;
    private String phoneNumber;
    private String profileImage; // this will be just a String path or filename
    private String newProfileImage;
}
