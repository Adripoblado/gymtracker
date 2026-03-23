package com.adripoblado.gymtracker.gymtracker.dto;

import java.util.List;

import com.adripoblado.gymtracker.gymtracker.model.enums.Equipment;
import com.adripoblado.gymtracker.gymtracker.model.enums.ExerciseType;
import com.adripoblado.gymtracker.gymtracker.model.enums.MuscleGroup;

public class ExerciseRequestDTO {

    private String name;
    private String description;
    private List<MuscleGroup> muscleGroup;
    private List<ExerciseType> exerciseType;
    private List<Equipment> equipment;

    public ExerciseRequestDTO() {
    }

    public ExerciseRequestDTO(String name, String description, List<MuscleGroup> muscleGroup, List<ExerciseType> exerciseType, List<Equipment> equipment) {
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
        this.exerciseType = exerciseType;
        this.equipment = equipment;
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

    public List<ExerciseType> getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(List<ExerciseType> exerciseType) {
        this.exerciseType = exerciseType;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

}
