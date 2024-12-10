package com.univerity.hallmanagement.controller;

import com.univerity.hallmanagement.dto.AuthenticationRequest;
import com.univerity.hallmanagement.entity.UniversityUser;
import com.univerity.hallmanagement.security.jwt.JwtUtil;
import com.univerity.hallmanagement.service.UserCreateService;
import com.univerity.hallmanagement.service.UserRegistrationService;
import com.univerity.hallmanagement.vo.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRegistrationService registrationService;
    private final JwtUtil jwtUtil;

    private final UserCreateService userCreateService;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            UserRegistrationService registrationService,
            JwtUtil jwtUtil, UserCreateService userCreateService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.userCreateService = userCreateService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<UniversityUser> registerUser(@RequestBody UniversityUser user) {
        UniversityUser registeredUser = registrationService.registerNewUser(user);
        return ResponseEntity.ok(registeredUser);
    }


    @PostMapping("/create")
    public ResponseEntity<String> createUser() {

        this.userCreateService.createUser();

        return ResponseEntity.ok("User Created");
    }
}