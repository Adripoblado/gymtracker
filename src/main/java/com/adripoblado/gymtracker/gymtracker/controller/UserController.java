package com.adripoblado.gymtracker.gymtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adripoblado.gymtracker.gymtracker.dto.UpdateUserDTO;
import com.adripoblado.gymtracker.gymtracker.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO entity) {
        System.out.println("Received update request for user ID: " + entity.getUsername());
        UpdateUserDTO response = userService.updateUser(entity);
        return ResponseEntity.ok(response);
    }
}
