package com.adripoblado.gymtracker.gymtracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adripoblado.gymtracker.gymtracker.model.Exercise;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.model.enums.Equipment;
import com.adripoblado.gymtracker.gymtracker.model.enums.ExerciseType;
import com.adripoblado.gymtracker.gymtracker.model.enums.MuscleGroup;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Optional<Exercise> findByName(String name);
    Optional<Exercise> findById(Long id);
    boolean existsByNameAndUser(String name, User user);
    Optional<List<Exercise>> findByIsCustom(boolean isCustom);
    Optional<List<Exercise>> findByExerciseType(ExerciseType exerciseType);
    Optional<List<Exercise>> findByEquipment(Equipment equipment);
    Optional<List<Exercise>> findByUser(User user);
    Optional<List<Exercise>> findByMuscleGroup(MuscleGroup muscleGroup);
    Optional<List<Exercise>> findByMuscleGroupAndExerciseType(MuscleGroup muscleGroup, ExerciseType exerciseType);
}
