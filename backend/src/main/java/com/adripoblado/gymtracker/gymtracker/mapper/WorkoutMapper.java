package com.adripoblado.gymtracker.gymtracker.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.adripoblado.gymtracker.gymtracker.dto.WorkoutExerciseResponseDTO;
import com.adripoblado.gymtracker.gymtracker.dto.WorkoutResponseDTO;
import com.adripoblado.gymtracker.gymtracker.dto.WorkoutSetResponseDTO;
import com.adripoblado.gymtracker.gymtracker.model.Workout;
import com.adripoblado.gymtracker.gymtracker.model.WorkoutExercise;
import com.adripoblado.gymtracker.gymtracker.model.WorkoutSet;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    @Mapping(source = "user.id", target = "userId")
    WorkoutResponseDTO toDto(Workout workout);

    @Mapping(source = "exercise.id", target = "exerciseId") 
    @Mapping(source = "exercise.name", target = "exerciseName")
    WorkoutExerciseResponseDTO toWorkoutExerciseDto(WorkoutExercise workoutExercise);

    WorkoutSetResponseDTO toWorkoutSetDto(WorkoutSet workoutSet);

    List<WorkoutResponseDTO> toDtoList(List<Workout> workouts);
}
