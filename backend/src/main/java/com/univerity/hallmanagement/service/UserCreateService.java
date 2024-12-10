package com.univerity.hallmanagement.service;

import com.univerity.hallmanagement.entity.UniversityUser;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService {

    private final UserRegistrationService registrationService;

    public UserCreateService(UserRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void createUser() {
        UniversityUser user = new UniversityUser();
        user.setUsername("johndoe");
        user.setPassword("rawPassword");
        user.setRole("ADMIN"); // or "USER"

// This will:
// 1. Check if username exists
// 2. Encode the password
// 3. Save the user
        registrationService.registerNewUser(user);

    }
}
