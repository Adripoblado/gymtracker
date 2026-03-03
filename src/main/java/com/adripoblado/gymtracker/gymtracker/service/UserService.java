package com.adripoblado.gymtracker.gymtracker.service;

import org.springframework.stereotype.Service;

import com.adripoblado.gymtracker.gymtracker.repository.UserRepository;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
