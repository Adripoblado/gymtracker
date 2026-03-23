package com.adripoblado.gymtracker.gymtracker.mapper;

import org.mapstruct.Mapper;

import com.adripoblado.gymtracker.gymtracker.dto.WorkoutResponseDTO;
import com.adripoblado.gymtracker.gymtracker.model.Workout;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    WorkoutResponseDTO toDto(Workout workout);
}
