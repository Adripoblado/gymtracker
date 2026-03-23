package com.adripoblado.gymtracker.gymtracker.dto;

import java.util.List;

public class WorkoutExerciseRequestDTO {

    private Long exerciseId;
    private int orderIndex;
    private String notes;

    private List<WorkoutSetRequestDTO> sets;

    public WorkoutExerciseRequestDTO() {
    }

    public WorkoutExerciseRequestDTO(Long exerciseId, int orderIndex, String notes, List<WorkoutSetRequestDTO> sets) {
        this.exerciseId = exerciseId;
        this.orderIndex = orderIndex;
        this.notes = notes;
        this.sets = sets;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<WorkoutSetRequestDTO> getSets() {
        return sets;
    }

    public void setSets(List<WorkoutSetRequestDTO> sets) {
        this.sets = sets;
    }
}
