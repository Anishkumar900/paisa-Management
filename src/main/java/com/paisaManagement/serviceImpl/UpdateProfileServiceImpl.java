package com.paisaManagement.serviceImpl;

import com.paisaManagement.exception.UserNotFoundException;
import com.paisaManagement.model.User;
import com.paisaManagement.repository.UserRepository;
import com.paisaManagement.request.UpdateProfile;
import com.paisaManagement.service.UpdateProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateProfileServiceImpl implements UpdateProfileService {
    private final UserRepository userRepository;

    @Override
    public void updateProfile(UpdateProfile updateProfile) {
        User user=userRepository.findByEmail(updateProfile.getEmail());
        user.setName(updateProfile.getName());
        user.setDeathOfBirth(updateProfile.getDeathOfBirth());
        user.setProfileImage(updateProfile.getProfileImage());
        user.setPhoneNumber(updateProfile.getPhoneNumber());
        userRepository.save(user);
    }

    @Override
    public void deleteProfile(Long id) {
        User user=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("user not found!"));
        userRepository.deleteById(id);
    }
}
