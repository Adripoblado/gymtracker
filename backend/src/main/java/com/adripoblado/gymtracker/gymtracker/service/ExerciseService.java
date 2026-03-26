package com.adripoblado.gymtracker.gymtracker.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.adripoblado.gymtracker.gymtracker.dto.CreateExerciseDTO;
import com.adripoblado.gymtracker.gymtracker.dto.ExerciseRequestDTO;
import com.adripoblado.gymtracker.gymtracker.dto.ExerciseResponseDTO;
import com.adripoblado.gymtracker.gymtracker.mapper.ExerciseMapper;
import com.adripoblado.gymtracker.gymtracker.model.Equipment;
import com.adripoblado.gymtracker.gymtracker.model.Exercise;
import com.adripoblado.gymtracker.gymtracker.model.ExerciseType;
import com.adripoblado.gymtracker.gymtracker.model.MuscleGroup;
import com.adripoblado.gymtracker.gymtracker.model.RoleEnum;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.repository.EquipmentRepository;
import com.adripoblado.gymtracker.gymtracker.repository.ExerciseRepository;
import com.adripoblado.gymtracker.gymtracker.repository.ExerciseTypeRepository;
import com.adripoblado.gymtracker.gymtracker.repository.MuscleGroupRepository;
import com.adripoblado.gymtracker.gymtracker.security.SecurityUtils;

import jakarta.transaction.Transactional;

@Service
public class ExerciseService {

    final ExerciseRepository exerciseRepository;
    final MuscleGroupRepository muscleGroupRepository;
    final ExerciseTypeRepository exerciseTypeRepository;
    final EquipmentRepository equipmentRepository;
    final SecurityUtils securityUtils;
    private final ExerciseMapper exerciseMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, MuscleGroupRepository muscleGroupRepository, ExerciseTypeRepository exerciseTypeRepository, EquipmentRepository equipmentRepository, SecurityUtils securityUtils, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.muscleGroupRepository = muscleGroupRepository;
        this.exerciseTypeRepository = exerciseTypeRepository;
        this.equipmentRepository = equipmentRepository;
        this.securityUtils = securityUtils;
        this.exerciseMapper = exerciseMapper;
    }

    @Transactional
    public String createExercise(CreateExerciseDTO request) {
        User user = securityUtils.getCurrentUser();

        if (user == null) {
            return "No user authenticated";
        }

        Exercise exercise = new Exercise(request.name(), request.description(), !user.getRole().equals("ADMIN"), user);

        Set<MuscleGroup> foundMuscles = new HashSet<>(muscleGroupRepository.findAllById(request.muscleGroupIds()));
        exercise.setMuscleGroup(foundMuscles);

        Set<ExerciseType> foundTypes = new HashSet<>(exerciseTypeRepository.findAllById(request.exerciseTypeIds()));
        exercise.setExerciseType(foundTypes);

        Set<Equipment> foundEquipments = new HashSet<>(equipmentRepository.findAllById(request.equipmentIds()));
        exercise.setEquipment(foundEquipments);

        exerciseRepository.save(exercise);
        return "Global exercise created successfully";
    }

    @Transactional
    public List<ExerciseResponseDTO> getExercises(Long muscleGroupId, Long exerciseTypeId, Long equipmentId){
        List<Exercise> exercises = exerciseRepository.findWithFilters(muscleGroupId, exerciseTypeId, equipmentId);
        return exerciseMapper.toDtoList(exercises);
    }

    @Transactional
    public List<ExerciseResponseDTO> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exerciseMapper.toDtoList(exercises);
    }

    @Transactional
    public String updateCustomExercise(ExerciseRequestDTO request) {
        Exercise exercise = exerciseRepository.findById(request.id()).orElseThrow(() -> new RuntimeException("Exercise not found"));
        User user = securityUtils.getCurrentUser();

        if (user == null) {
            return "No user authenticated";
        }
        
        if (exercise.isCustom() && exercise.getUser().getUsername().equals(user.getUsername())) {
            exercise.setName(request.name());

            Set<MuscleGroup> foundMuscles = new HashSet<>(muscleGroupRepository.findAllById(request.muscleGroupIds()));
            exercise.setMuscleGroup(foundMuscles);

            Set<ExerciseType> foundTypes = new HashSet<>(exerciseTypeRepository.findAllById(request.exerciseTypeIds()));
            exercise.setExerciseType(foundTypes);

            Set<Equipment> foundEquipments = new HashSet<>(equipmentRepository.findAllById(request.equipmentIds()));
            exercise.setEquipment(foundEquipments);
            
            exerciseRepository.save(exercise);
        } else {
            return "Unauthorized to update this exercise";
        }

        return "Exercise updated successfully";
    }

    @Transactional
    public String deleteExercise(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);

        if (exercise == null) {
            return "Exercise not found";
        }

        User user = securityUtils.getCurrentUser();

        if (user == null) {
            return "Unauthorized";
        }
        
        if (user.getRole().equals(RoleEnum.ADMIN.name())) {
            System.out.println("ADMIN deleted: " + exercise.getName());
            exerciseRepository.delete(exercise);
        } else {
            if (exercise.getUser().getUsername().equals(user.getUsername())) {
                System.out.println(user.getUsername() + " deleted: " + exercise.getName());
                exerciseRepository.delete(exercise);
            } else {
                return "Unauthorized to delete this exercise";
            }
        }
        
        return "Exercise deleted successfully";
    }
}
