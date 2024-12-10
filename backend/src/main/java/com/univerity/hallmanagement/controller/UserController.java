package com.univerity.hallmanagement.controller;

import com.univerity.hallmanagement.entity.UniversityUser;
import com.univerity.hallmanagement.repository.UniversityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UniversityUserRepository universityUserRepository;

    @GetMapping
    public List<UniversityUser> getAllUsers() {
        return universityUserRepository.findAll();
    }
}