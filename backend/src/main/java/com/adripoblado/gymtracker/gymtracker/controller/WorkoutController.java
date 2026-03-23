package com.adripoblado.gymtracker.gymtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adripoblado.gymtracker.gymtracker.dto.WorkoutRequestDTO;
import com.adripoblado.gymtracker.gymtracker.dto.WorkoutResponseDTO;
import com.adripoblado.gymtracker.gymtracker.service.WorkoutService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWorkout(@RequestBody WorkoutRequestDTO request) {        
        return ResponseEntity.ok(workoutService.createWorkout(request));
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<WorkoutResponseDTO>> listSelfWorkoutRecords() {
        return ResponseEntity.ok(workoutService.getWorkoutsForCurrentUser());
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long id) {
        return ResponseEntity.ok(workoutService.deleteWorkout(id));
    }
}
