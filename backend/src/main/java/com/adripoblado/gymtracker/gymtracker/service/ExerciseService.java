package com.adripoblado.gymtracker.gymtracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adripoblado.gymtracker.gymtracker.dto.CreateExerciseDTO;
import com.adripoblado.gymtracker.gymtracker.dto.ExerciseRequestDTO;
import com.adripoblado.gymtracker.gymtracker.dto.ExerciseResponseDTO;
import com.adripoblado.gymtracker.gymtracker.mapper.ExerciseMapper;
import com.adripoblado.gymtracker.gymtracker.model.Exercise;
import com.adripoblado.gymtracker.gymtracker.model.RoleEnum;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.model.enums.ExerciseType;
import com.adripoblado.gymtracker.gymtracker.model.enums.MuscleGroup;
import com.adripoblado.gymtracker.gymtracker.repository.ExerciseRepository;
import com.adripoblado.gymtracker.gymtracker.security.SecurityUtils;

import jakarta.transaction.Transactional;

@Service
public class ExerciseService {

    final ExerciseRepository exerciseRepository;
    final SecurityUtils securityUtils;
    private final ExerciseMapper exerciseMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, SecurityUtils securityUtils, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.securityUtils = securityUtils;
        this.exerciseMapper = exerciseMapper;
    }

    @Transactional
    public String createGlobalExercise(CreateExerciseDTO request) {
        System.out.println("Attempting to create global exercise: " + request.name());
        Exercise exercise = new Exercise(request, null);
        exerciseRepository.save(exercise);
        return "Global exercise created successfully";
    }

    @Transactional
    public String createCustomExercise(CreateExerciseDTO request, User user) {
        Exercise exercise = new Exercise(request, user);

        if (exerciseRepository.existsByNameAndUser(request.name(), user)) {
            return "Custom exercise with this name already exists";
        }

        exerciseRepository.save(exercise);
        return "Custom exercise created successfully";
    }

    @Transactional
    public List<ExerciseResponseDTO> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exerciseMapper.toDtoList(exercises);
    }


    @Transactional
    public List<ExerciseResponseDTO> getAllGlobalExercises() {
        List<Exercise> exercises = exerciseRepository.findByIsCustom(false).orElse(List.of());
        return exerciseMapper.toDtoList(exercises);
    }

    @Transactional
    public List<ExerciseResponseDTO> getAllCustomExercises() {
        List<Exercise> exercises = exerciseRepository.findByUser(securityUtils.getCurrentUser()).orElse(List.of());
        return exerciseMapper.toDtoList(exercises);
    }

    @Transactional
    public List<ExerciseResponseDTO> getCustomExercise(MuscleGroup muscleGroup, ExerciseType exerciseType) {
        List<Exercise> exercises = exerciseRepository.findByMuscleGroupAndExerciseType(muscleGroup, exerciseType).orElse(List.of());
        return exerciseMapper.toDtoList(exercises);
    }

    @Transactional
    public String updateCustomExercise(Long exerciseId, ExerciseRequestDTO request, User user) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new RuntimeException("Exercise not found"));
        
        if (exercise.isCustom() && exercise.getUser().getUsername().equals(user.getUsername())) {
            exercise.setName(request.getName());
            exercise.setMuscleGroup(request.getMuscleGroup());
            exercise.setExerciseType(request.getExerciseType());
            exercise.setEquipment(request.getEquipment());
            exerciseRepository.save(exercise);
        } else {
            return "Unauthorized to update this exercise";
        }

        return "Custom exercise updated successfully";
    }

    @Transactional
    public String deleteExercise(Long exerciseId, User user) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);

        if (exercise == null) {
            return "Exercise not found";
        }
        
        if (user.getRole().equals(RoleEnum.ADMIN.name())) {
            exerciseRepository.delete(exercise);
        } else {
            if (exercise.isCustom() && exercise.getUser().getUsername().equals(user.getUsername())) {
                exerciseRepository.delete(exercise);
            } else {
                return "Unauthorized to delete this exercise";
            }
        }
        
        return "Exercise deleted successfully";
    }
}
