package com.adripoblado.gymtracker.gymtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adripoblado.gymtracker.gymtracker.dto.CreateExerciseDTO;
import com.adripoblado.gymtracker.gymtracker.dto.ExerciseRequestDTO;
import com.adripoblado.gymtracker.gymtracker.dto.ExerciseResponseDTO;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.security.SecurityUtils;
import com.adripoblado.gymtracker.gymtracker.service.ExerciseService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final SecurityUtils securityUtils;

    public ExerciseController(ExerciseService exerciseService, SecurityUtils securityUtils) {
        this.exerciseService = exerciseService;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/get")
    public ResponseEntity<List<ExerciseResponseDTO>> getExercises(@RequestParam(required = false) Long muscleGroupId, @RequestParam(required = false) Long exerciseTypeId, @RequestParam(required = false) Long equipmentId) {        
        return ResponseEntity.ok(exerciseService.getExercises(muscleGroupId, exerciseTypeId, equipmentId));
    }
    

    @GetMapping("/list")
    public ResponseEntity<List<ExerciseResponseDTO>> listAllExercises() {
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }
    
    @PostMapping("/create")
    public ResponseEntity<String> createGlobalExercise(@RequestBody CreateExerciseDTO request) {
        System.out.println(request.toString());
        return ResponseEntity.ok(exerciseService.createGlobalExercise(request));
    }

    @PutMapping("modify/{id}")
    public ResponseEntity<?> modifyExercise(@PathVariable Long id, @RequestBody ExerciseRequestDTO request) {
        User user = securityUtils.getCurrentUser();

        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String response = exerciseService.updateCustomExercise(id, request, user);

        if (response.contains("successfully")) {
            return ResponseEntity.ok(response);
        } else if (response.contains("Unauthorized")) {
            return ResponseEntity.status(403).body(response);
        } else {
            return ResponseEntity.status(400).body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long id) {
        User user = securityUtils.getCurrentUser();

        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String response = exerciseService.deleteExercise(id, user);

        if (response.contains("successfully")) {
            return ResponseEntity.ok(response);
        } else if (response.contains("Unauthorized")) {
            return ResponseEntity.status(403).body(response);
        } else {
            return ResponseEntity.status(400).body(response);
        }
    }
}
