package com.adripoblado.gymtracker.gymtracker.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adripoblado.gymtracker.gymtracker.dto.AuthResponseDTO;
import com.adripoblado.gymtracker.gymtracker.dto.LoginRequestDTO;
import com.adripoblado.gymtracker.gymtracker.dto.RegisterRequestDTO;
import com.adripoblado.gymtracker.gymtracker.model.RoleEnum;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${auth.admin.code}")
    private final String adminCode;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, @Value("${auth.admin.code}") String adminCode) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.adminCode = adminCode;
    }

    public String register(RegisterRequestDTO request) {
        String username = request.username();
        String email = request.email();
        String password = request.password();

        if (username == null || email == null || password == null) {
            return "Username, email, and password are required.";
        }

        if (userRepository.existsByUsername(username) || userRepository.existsByEmail(email)) {
            return "Username or email already exists.";
        }

        String encodedPassword = passwordEncoder.encode(password);

        RoleEnum role = RoleEnum.USER; // Default role for new users

        if (request.rolecode() != null && request.rolecode().equals(adminCode)) {
            role = RoleEnum.ADMIN; // Assign admin role if the correct code is provided
        }

        userRepository.save(new User(username, email, encodedPassword, role.name()));
        return "User registered successfully.";
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        String username = request.username();
        String password = request.password();

        Optional<User> user = null;
        
        if (username.contains("@")) {
            user = userRepository.findByEmail(username);
        } else {
            user = userRepository.findByUsername(username);
        }

        try {
            user.get();        
        } catch (NoSuchElementException e) {
            return null;
        }

        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.get().getUsername(), password);
            authenticationManager.authenticate(authToken);
        } catch (Exception e) {
            return null;
        }    

        return new AuthResponseDTO(jwtService.generateToken(user.get()), user.get().getId(), username, user.get().getRole());   
    }
}
