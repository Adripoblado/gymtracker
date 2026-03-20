package com.adripoblado.gymtracker.gymtracker.dto;

import java.util.List;

import com.adripoblado.gymtracker.gymtracker.model.enums.Equipment;
import com.adripoblado.gymtracker.gymtracker.model.enums.ExerciseType;
import com.adripoblado.gymtracker.gymtracker.model.enums.MuscleGroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateExerciseDTO(
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    String name,

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must be at most 500 characters")
    String description,

    @NotNull(message = "Muscle group is required")
    List<MuscleGroup> muscleGroup,

    @NotNull(message = "Exercise type is required")
    List<ExerciseType> exerciseType,

    @NotNull(message = "Equipment is required")
    List<Equipment> equipment
) {
}
