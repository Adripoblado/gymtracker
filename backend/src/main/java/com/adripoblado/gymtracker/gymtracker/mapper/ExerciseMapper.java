package com.adripoblado.gymtracker.gymtracker.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.adripoblado.gymtracker.gymtracker.dto.ExerciseResponseDTO;
import com.adripoblado.gymtracker.gymtracker.model.Exercise;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    
    @Mapping(source = "user.id", target = "userId")
    ExerciseResponseDTO toDto(Exercise exercise);

    List<ExerciseResponseDTO> toDtoList(List<Exercise> exercises);
}
