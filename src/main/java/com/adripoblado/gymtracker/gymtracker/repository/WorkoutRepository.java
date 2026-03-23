package com.adripoblado.gymtracker.gymtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adripoblado.gymtracker.gymtracker.model.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findByUserId(Long userId);
}
