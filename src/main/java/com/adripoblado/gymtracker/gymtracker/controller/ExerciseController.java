package com.adripoblado.gymtracker.gymtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adripoblado.gymtracker.gymtracker.dto.CreateExerciseDTO;
import com.adripoblado.gymtracker.gymtracker.dto.ExerciseResponseDTO;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.security.SecurityUtils;
import com.adripoblado.gymtracker.gymtracker.service.ExerciseService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final SecurityUtils securityUtils;

    public ExerciseController(ExerciseService exerciseService, SecurityUtils securityUtils) {
        this.exerciseService = exerciseService;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/list/global")
    public ResponseEntity<List<ExerciseResponseDTO>> listGlobalExercises() {
        return ResponseEntity.ok(exerciseService.getAllGlobalExercises());
    }
    

    @PostMapping("/create/global")
    public ResponseEntity<String> createGlobalExercise(@Valid @RequestBody CreateExerciseDTO request) {
        User user = securityUtils.getCurrentUser();

        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        if (!user.getRole().equals("ADMIN")) {
            return ResponseEntity.status(403).body("Forbidden: Only admins can create global exercises");
        }

        System.out.println("Creating global exercise: " + request.name() + " by admin: " + user.getUsername());
        return ResponseEntity.ok(exerciseService.createGlobalExercise(request));
    }
    
}
