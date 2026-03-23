package com.adripoblado.gymtracker.gymtracker.dto;

import java.util.List;

public class WorkoutResponseDTO {

    private Long id;
    private String name;
    private String startTime;
    private String endTime;
    private List<WorkoutExerciseResponseDTO> exercises;

    public WorkoutResponseDTO() {
    }

    public WorkoutResponseDTO(Long id, String name, String startTime, String endTime, List<WorkoutExerciseResponseDTO> exercises) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.exercises = exercises;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<WorkoutExerciseResponseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<WorkoutExerciseResponseDTO> exercises) {
        this.exercises = exercises;
    }

    
}
