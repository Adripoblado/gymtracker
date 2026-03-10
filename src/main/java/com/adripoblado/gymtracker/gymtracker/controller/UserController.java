package com.adripoblado.gymtracker.gymtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adripoblado.gymtracker.gymtracker.dto.UpdateUserDTO;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.security.SecurityUtils;
import com.adripoblado.gymtracker.gymtracker.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SecurityUtils securityUtils;

    public UserController(UserService userService, SecurityUtils securityUtils) {
        this.userService = userService;
        this.securityUtils = securityUtils;
    }

    @PutMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO entity) {
        User user = securityUtils.getCurrentUser();
        UpdateUserDTO response = new UpdateUserDTO();

        if (user == null) {
            response.setName("Unauthorized");
            return ResponseEntity.status(401).body(response);
        }

        response = userService.updateUser(entity);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getMethodName() {
        User user = securityUtils.getCurrentUser();

        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        System.out.println("Authenticated user: " + user.getUsername());
        System.out.println("Authenticated user details: " + user.toString());
        return ResponseEntity.ok("Authenticated user: " + user.getUsername());
    }
    
}
