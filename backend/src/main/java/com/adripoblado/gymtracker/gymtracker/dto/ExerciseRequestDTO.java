package com.adripoblado.gymtracker.gymtracker.dto;

import java.util.Set;

import com.adripoblado.gymtracker.gymtracker.model.Equipment;
import com.adripoblado.gymtracker.gymtracker.model.ExerciseType;
import com.adripoblado.gymtracker.gymtracker.model.MuscleGroup;

public class ExerciseRequestDTO {

    private String name;
    private String description;
    private Set<MuscleGroup> muscleGroup;
    private Set<ExerciseType> exerciseType;
    private Set<Equipment> equipment;

    public ExerciseRequestDTO() {
    }

    public ExerciseRequestDTO(String name, String description, Set<MuscleGroup> muscleGroup, Set<ExerciseType> exerciseType, Set<Equipment> equipment) {
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

    public Set<MuscleGroup> getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(Set<MuscleGroup> muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public Set<ExerciseType> getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(Set<ExerciseType> exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Set<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(Set<Equipment> equipment) {
        this.equipment = equipment;
    }

}
