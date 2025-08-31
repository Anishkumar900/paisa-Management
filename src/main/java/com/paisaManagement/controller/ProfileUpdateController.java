package com.paisaManagement.controller;

import com.paisaManagement.cloudinary.CloudinaryService;
import com.paisaManagement.request.UpdateProfile;
import com.paisaManagement.service.UpdateProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ProfileUpdateController {

    private final CloudinaryService cloudinaryService;
    private final UpdateProfileService updateProfileService;

    @PatchMapping(value = "/update-profile")
    public ResponseEntity<?> updateProfile(
            @RequestHeader("Authorization") String token,
            @RequestPart("updateProfile") UpdateProfile updateProfile,
            @RequestPart(value = "newProfileImage", required = false) MultipartFile newProfileImage) {
        try {
//            System.out.println("test");
            System.out.println(newProfileImage);
            if (newProfileImage != null && !newProfileImage.isEmpty()) {
//                System.out.println("test1");
                String profileUrl = cloudinaryService.uploadFile(newProfileImage);
//                System.out.println(profileUrl);
                updateProfile.setProfileImage(profileUrl);
            }
//            System.out.println("tes2");
            updateProfileService.updateProfile(updateProfile);
            return ResponseEntity.ok("Profile updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }



    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteProfile(@RequestHeader("Authorization") String token,
                                           @PathVariable Long id){
        try{
            updateProfileService.deleteProfile(id);
            return new ResponseEntity<>("Delete Successful.",HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
