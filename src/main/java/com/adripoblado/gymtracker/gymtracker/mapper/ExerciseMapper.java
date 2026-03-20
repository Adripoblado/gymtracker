package com.adripoblado.gymtracker.gymtracker.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.adripoblado.gymtracker.gymtracker.dto.ExerciseResponseDTO;
import com.adripoblado.gymtracker.gymtracker.model.Exercise;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    ExerciseResponseDTO toDto(Exercise exercise);

    List<ExerciseResponseDTO> toDtoList(List<Exercise> exercises);
}
