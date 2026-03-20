package com.adripoblado.gymtracker.gymtracker.service;

import org.springframework.stereotype.Service;

import com.adripoblado.gymtracker.gymtracker.dto.UpdateUserDTO;
import com.adripoblado.gymtracker.gymtracker.mapper.UserMapper;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.repository.UserRepository;
import com.adripoblado.gymtracker.gymtracker.security.SecurityUtils;

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

    @Transactional
    public UpdateUserDTO updateUser(UpdateUserDTO dto) {
        User user = securityUtils.getCurrentUser();

        if (user == null) {
            return null;
        }

        userMapper.updateUserFromDTO(dto, user);

        User updatedUser = userRepository.save(user);
    
        return userMapper.toResponseDTO(updatedUser);
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
