package com.adripoblado.gymtracker.gymtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adripoblado.gymtracker.gymtracker.dto.UpdateUserDTO;
import com.adripoblado.gymtracker.gymtracker.mapper.UserMapper;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.security.SecurityUtils;
import com.adripoblado.gymtracker.gymtracker.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SecurityUtils securityUtils;
    private final UserMapper userMapper;

    public UserController(UserService userService, SecurityUtils securityUtils, UserMapper userMapper) {
        this.userService = userService;
        this.securityUtils = securityUtils;
        this.userMapper = userMapper;
    }

    @PutMapping("/me/update")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO entity) {
        UpdateUserDTO response = userService.updateUser(entity);
        if (response == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getSelfData() {
        User user = securityUtils.getCurrentUser();

        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        return ResponseEntity.ok(userMapper.toResponseDTO(user));
    }
    
    @DeleteMapping("/me/delete")
    public ResponseEntity<String> deleteSelfUser(@RequestParam String confirm) {
        User user = securityUtils.getCurrentUser();

        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        if (!"DELETE ACCOUNT".equals(confirm)) {
            return ResponseEntity.status(400).body("Invalid confirmation message");
        }

        userService.deleteUser(user);
        return ResponseEntity.ok("User deleted successfully");
    }

    @DeleteMapping("/admin/delete/{username}")
    public ResponseEntity<String> deleteUserById(@PathVariable String username) {
        if (!securityUtils.getCurrentUser().getRole().equals("ADMIN")) {
            return ResponseEntity.status(403).body("Forbidden: Only admins can delete users");
        }

        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        userService.deleteUser(user);
        return ResponseEntity.ok("User deleted successfully");
    }
}
