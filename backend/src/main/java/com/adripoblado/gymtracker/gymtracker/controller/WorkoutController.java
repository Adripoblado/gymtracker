package com.adripoblado.gymtracker.gymtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adripoblado.gymtracker.gymtracker.dto.WorkoutRequestDTO;
import com.adripoblado.gymtracker.gymtracker.dto.WorkoutResponseDTO;
import com.adripoblado.gymtracker.gymtracker.service.WorkoutService;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWorkout(@RequestBody WorkoutRequestDTO request) {        
        String response = workoutService.createWorkout(request);
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
    
    @GetMapping("/list")
    public ResponseEntity<List<WorkoutResponseDTO>> listSelfWorkoutRecords() {
        return ResponseEntity.ok(workoutService.getWorkoutsForCurrentUser());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getFilteredWorkouts(@RequestParam(required = false) String startDate, 
        @RequestParam(required = false) String endDate, 
        @RequestParam(required = false) Long muscleGroupId
    ) {
        List<WorkoutResponseDTO> workouts = workoutService.getFilteredWorkouts(startDate, endDate, muscleGroupId);
        return ResponseEntity.ok(workouts);
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long id) {
        String response = workoutService.deleteWorkout(id);
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
