package com.univerity.hallmanagement.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;

    // Constructors
    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}