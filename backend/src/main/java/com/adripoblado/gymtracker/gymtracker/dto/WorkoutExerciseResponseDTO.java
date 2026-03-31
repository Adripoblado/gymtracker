package com.adripoblado.gymtracker.gymtracker.dto;

import java.util.List;

public class WorkoutExerciseResponseDTO {

    private Long exerciseId;
    private String exerciseName;
    private int orderIndex;
    private int setAmount;
    private List<WorkoutSetResponseDTO> sets;

    public WorkoutExerciseResponseDTO() {
    }

        public WorkoutExerciseResponseDTO(Long exerciseId, String exerciseName, int orderIndex, int setAmount, List<WorkoutSetResponseDTO> sets) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.orderIndex = orderIndex;
        this.setAmount = setAmount;
        this.sets = sets;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
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

    @Override
    public String toString() {
        return "WorkoutExerciseResponseDTO{" +
                "exerciseId=" + exerciseId +
                ", orderIndex=" + orderIndex +
                ", setAmount=" + setAmount +
                ", sets=" + sets +
                '}';
    }
}
