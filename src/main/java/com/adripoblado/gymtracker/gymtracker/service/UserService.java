package com.adripoblado.gymtracker.gymtracker.service;

import org.springframework.stereotype.Service;

import com.adripoblado.gymtracker.gymtracker.dto.UpdateUserDTO;
import com.adripoblado.gymtracker.gymtracker.mapper.UserMapper;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.repository.UserRepository;
import com.adripoblado.gymtracker.gymtracker.security.SecurityUtils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserMapper userMapper;
    final SecurityUtils securityUtils;

    public UserService(UserRepository userRepository, UserMapper userMapper, SecurityUtils securityUtils) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.securityUtils = securityUtils;
    }

    @SuppressWarnings("null")
    @Transactional
    public UpdateUserDTO updateUser(UpdateUserDTO dto) {
        String username = securityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        userMapper.updateUserFromDTO(dto, user);
        User updatedUser = userRepository.save(user);
    
        return userMapper.toResponseDTO(updatedUser);
    }
}
