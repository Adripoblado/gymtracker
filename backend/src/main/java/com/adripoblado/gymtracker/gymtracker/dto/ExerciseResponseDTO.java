package com.adripoblado.gymtracker.gymtracker.dto;

import java.util.List;

import com.adripoblado.gymtracker.gymtracker.model.Equipment;
import com.adripoblado.gymtracker.gymtracker.model.ExerciseType;
import com.adripoblado.gymtracker.gymtracker.model.MuscleGroup;

public class ExerciseResponseDTO {

    private long id;
    private String name;
    private String description;
    private List<MuscleGroup> muscleGroup;
    private List<Equipment> equipment;
    private List<ExerciseType> exerciseType;
    private Long userId;

    public ExerciseResponseDTO() {
    }

    public ExerciseResponseDTO(long id, String name, String description, List<MuscleGroup> muscleGroup, List<Equipment> equipment, List<ExerciseType> exerciseType, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
        this.equipment = equipment;
        this.exerciseType = exerciseType;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MuscleGroup> getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(List<MuscleGroup> muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public List<ExerciseType> getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(List<ExerciseType> exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ID: " + id + 
            " Name: " + name + 
            " User ID: " + userId;
    }
}
