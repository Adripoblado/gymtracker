package com.adripoblado.gymtracker.gymtracker.dto;

import java.time.LocalDateTime;
import java.util.List;

public class WorkoutRequestDTO {

    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<WorkoutExerciseRequestDTO> exercises;

    public WorkoutRequestDTO() {
    }

    public WorkoutRequestDTO(String name, LocalDateTime startTime, LocalDateTime endTime, List<WorkoutExerciseRequestDTO> exercises) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<WorkoutExerciseRequestDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<WorkoutExerciseRequestDTO> exercises) {
        this.exercises = exercises;
    }

    
}
