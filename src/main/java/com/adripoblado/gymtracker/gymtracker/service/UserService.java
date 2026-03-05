package com.adripoblado.gymtracker.gymtracker.service;

import org.springframework.stereotype.Service;

import com.adripoblado.gymtracker.gymtracker.dto.UpdateUserDTO;
import com.adripoblado.gymtracker.gymtracker.mapper.UserMapper;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    final UserRepository userRepository;

    final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @SuppressWarnings("null")
    @Transactional
    public UpdateUserDTO updateUser(UpdateUserDTO entity) {
        User user = userRepository.findByUsername(entity.getUsername()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        userMapper.updateUserFromDTO(entity, user);
        User updatedUser = userRepository.save(user);
    
        return userMapper.toResponseDTO(updatedUser);
    }
}
