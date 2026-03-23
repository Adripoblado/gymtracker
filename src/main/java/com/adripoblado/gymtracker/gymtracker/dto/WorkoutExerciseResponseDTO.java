package com.adripoblado.gymtracker.gymtracker.dto;

import java.util.List;

public class WorkoutExerciseResponseDTO {

    private Long id;
    private int orderIndex;
    private int setAmount;
    private List<WorkoutSetResponseDTO> sets;

    public WorkoutExerciseResponseDTO() {
    }

        public WorkoutExerciseResponseDTO(Long id, int orderIndex, int setAmount, List<WorkoutSetResponseDTO> sets) {
        this.id = id;
        this.orderIndex = orderIndex;
        this.setAmount = setAmount;
        this.sets = sets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public int getSetAmount() {
        return setAmount;
    }

    public void setSetAmount(int setAmount) {
        this.setAmount = setAmount;
    }

    public List<WorkoutSetResponseDTO> getSets() {
        return sets;
    }

    public void setSets(List<WorkoutSetResponseDTO> sets) {
        this.sets = sets;
    }
}
