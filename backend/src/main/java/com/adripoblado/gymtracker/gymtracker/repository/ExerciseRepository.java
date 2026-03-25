package com.adripoblado.gymtracker.gymtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adripoblado.gymtracker.gymtracker.model.Exercise;
import com.adripoblado.gymtracker.gymtracker.model.User;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    public boolean existsByNameAndUser(String name, User user);

    @Query("SELECT DISTINCT e FROM Exercise e " +
        "LEFT JOIN FETCH e.muscleGroup " +
        "LEFT JOIN FETCH e.exerciseType " +
        "LEFT JOIN FETCH e.equipment " +
        "LEFT JOIN e.muscleGroup mg " + 
        "LEFT JOIN e.exerciseType et " + 
        "LEFT JOIN e.equipment eq " + 
        "WHERE (:muscleId IS NULL OR mg.id = :muscleId) " + 
        "AND (:typeId IS NULL OR et.id = :typeId) " + 
        "AND (:equipmentId IS NULL OR eq.id = :equipmentId)")
    List<Exercise> findWithFilters(
        @Param("muscleId") Long muscleId, 
        @Param("typeId") Long typeId, 
        @Param("equipmentId") Long equipmentId
    );
}
