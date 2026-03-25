package com.adripoblado.gymtracker.gymtracker.dto;

import java.util.List;
import java.util.Set;

import com.adripoblado.gymtracker.gymtracker.model.Equipment;
import com.adripoblado.gymtracker.gymtracker.model.ExerciseType;
import com.adripoblado.gymtracker.gymtracker.model.MuscleGroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExerciseRequestDTO(
    Long id,

    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Description is required")
    String description,

    @NotNull(message = "Muscle group IDs are required")
    List<Long> muscleGroupIds,

    @NotNull(message = "Exercise type IDs are required")
    List<Long> exerciseTypeIds,

    @NotNull(message = "Equipment IDs are required")
    List<Long> equipmentIds
) {}
