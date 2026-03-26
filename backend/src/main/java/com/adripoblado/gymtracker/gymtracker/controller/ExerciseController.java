package com.adripoblado.gymtracker.gymtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adripoblado.gymtracker.gymtracker.dto.CreateExerciseDTO;
import com.adripoblado.gymtracker.gymtracker.dto.ExerciseRequestDTO;
import com.adripoblado.gymtracker.gymtracker.dto.ExerciseResponseDTO;
import com.adripoblado.gymtracker.gymtracker.service.ExerciseService;

import java.util.List;
import java.util.Map;

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

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
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
    public ResponseEntity<?> createExercise(@RequestBody CreateExerciseDTO request) {
        String response = exerciseService.createExercise(request);
        Map<String, String> mapResponse = Map.of("message", response);

        if (response.contains("successfully")) {
            return ResponseEntity.ok(mapResponse);
        } else if (response.contains("Unauthorized")) {
            return ResponseEntity.status(403).body(mapResponse);
        } else if (response.contains("No user authenticated")) {
            return ResponseEntity.status(401).body(mapResponse);
        } else {
            return ResponseEntity.status(400).body(Map.of("message", "Unkown Error"));
        }
    }

    @PutMapping("modify/{id}")
    public ResponseEntity<?> modifyExercise(@RequestBody ExerciseRequestDTO request) {
        String response = exerciseService.updateCustomExercise(request);
        Map<String, String> mapResponse = Map.of("message", response);

        if (response.contains("successfully")) {
            return ResponseEntity.ok(mapResponse);
        } else if (response.contains("Unauthorized")) {
            return ResponseEntity.status(403).body(mapResponse);
        } else if (response.contains("No user authenticated")) {
            return ResponseEntity.status(401).body(mapResponse);
        } else {
            return ResponseEntity.status(400).body(Map.of("message", "Unkown Error"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
        String response = exerciseService.deleteExercise(id);
        Map<String, String> mapResponse = Map.of("message", response);

        if (response.contains("successfully")) {
            return ResponseEntity.ok(mapResponse);
        } else if (response.contains("Unauthorized")) {
            return ResponseEntity.status(403).body(mapResponse);
        } else {
            return ResponseEntity.status(400).body(mapResponse);
        }
    }
}
