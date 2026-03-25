package com.adripoblado.gymtracker.gymtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.adripoblado.gymtracker.gymtracker.model.MuscleGroup;

public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {
   
}
