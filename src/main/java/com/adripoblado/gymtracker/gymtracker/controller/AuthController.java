package com.adripoblado.gymtracker.gymtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adripoblado.gymtracker.gymtracker.dto.LoginRequestDTO;
import com.adripoblado.gymtracker.gymtracker.dto.RegisterRequestDTO;
import com.adripoblado.gymtracker.gymtracker.service.AuthenticationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO entity) {
        boolean success = authenticationService.register(entity);
        if (success) {
            return "User registered successfully!";
        } else {
            return "Username or email already exists.";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO entity) {
        boolean success = authenticationService.login(entity);
        if (success) {
            System.out.println("User logged in successfully: " + entity.username());
            return "Login successful!";
        } else {
            System.out.println("Failed login attempt for username: " + entity.username());
            return "Invalid username or password.";
        }
    }
    
}
