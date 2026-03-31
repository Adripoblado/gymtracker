package com.adripoblado.gymtracker.gymtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adripoblado.gymtracker.gymtracker.dto.AuthResponseDTO;
import com.adripoblado.gymtracker.gymtracker.dto.LoginRequestDTO;
import com.adripoblado.gymtracker.gymtracker.dto.RegisterRequestDTO;
import com.adripoblado.gymtracker.gymtracker.service.AuthenticationService;

import org.springframework.http.ResponseEntity;
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
        return authenticationService.register(entity);
    }

    @PostMapping("/login") //TODO: Change to /login and return 401 on failure, 200 with token on success
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO entity) {
        System.out.println("Login attempt for username: " + entity.username());
        AuthResponseDTO response = authenticationService.login(entity);
        if (response.getToken() == null) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
        return ResponseEntity.ok(response);
    }
    
}
