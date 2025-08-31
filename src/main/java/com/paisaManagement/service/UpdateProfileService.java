package com.paisaManagement.service;

import com.paisaManagement.request.UpdateProfile;

public interface UpdateProfileService {
    void updateProfile(UpdateProfile updateProfile);
    void deleteProfile(Long id);
}
