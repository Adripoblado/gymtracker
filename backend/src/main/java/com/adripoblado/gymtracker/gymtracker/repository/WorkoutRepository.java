package com.adripoblado.gymtracker.gymtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adripoblado.gymtracker.gymtracker.model.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findByUserId(Long userId);

    @Query("SELECT DISTINCT w FROM Workout w " +
       "LEFT JOIN FETCH w.exercises we " +
       "LEFT JOIN FETCH we.exercise e " +
       "LEFT JOIN FETCH e.muscleGroup " +
       "LEFT JOIN we.exercise ex_filter " + 
       "LEFT JOIN ex_filter.muscleGroup mg " +  
       "WHERE w.user.id = :userId " + 
       "AND (:muscleGroupId IS NULL OR mg.id = :muscleGroupId)")
    List<Workout> findWorkoutsWithFilters(
        @Param("userId") Long userId, 
        @Param("muscleGroupId") Long muscleGroupId
    );
}
