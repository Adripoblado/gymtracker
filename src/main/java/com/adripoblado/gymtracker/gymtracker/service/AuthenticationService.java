package com.adripoblado.gymtracker.gymtracker.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public boolean register(RegisterRequestDTO request) {
        String username = request.username();
        String email = request.email();
        String password = request.password();

        if (userRepository.findByUsername(username) != null || userRepository.findByEmail(email) != null) {
            return false;
        }

        String encodedPassword = passwordEncoder.encode(password);
        userRepository.save(new User(username, email, encodedPassword, RoleEnum.USER.name()));
        return true;
    }

    public String login(LoginRequestDTO request) {
        String username = request.username();
        String password = request.password();

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (Exception e) {
            return null;
        }    
        return jwtService.generateToken(username);   
    }
}
