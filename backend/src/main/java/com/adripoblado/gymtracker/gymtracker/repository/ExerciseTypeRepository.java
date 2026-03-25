package com.adripoblado.gymtracker.gymtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adripoblado.gymtracker.gymtracker.model.ExerciseType;

public interface ExerciseTypeRepository extends JpaRepository<ExerciseType, Long> {

}
