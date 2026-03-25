package com.adripoblado.gymtracker.gymtracker.dto;

import java.util.List;

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
    List<Long> muscleGroupIds,

    @NotNull(message = "Exercise type is required")
    List<Long> exerciseTypeIds,

    @NotNull(message = "Equipment is required")
    List<Long> equipmentIds
) {
}
