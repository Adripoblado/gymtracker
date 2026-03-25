package com.adripoblado.gymtracker.gymtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adripoblado.gymtracker.gymtracker.model.Equipment;
import com.adripoblado.gymtracker.gymtracker.model.ExerciseType;
import com.adripoblado.gymtracker.gymtracker.model.MuscleGroup;
import com.adripoblado.gymtracker.gymtracker.repository.EquipmentRepository;
import com.adripoblado.gymtracker.gymtracker.repository.ExerciseTypeRepository;
import com.adripoblado.gymtracker.gymtracker.repository.MuscleGroupRepository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/catalogs")
public class ExerciseFilterCatalogController {

    private final MuscleGroupRepository muscleGroupRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;
    private final EquipmentRepository equipmentRepository;

    public ExerciseFilterCatalogController(MuscleGroupRepository muscleGroupRepository, ExerciseTypeRepository exerciseTypeRepository, EquipmentRepository equipmentRepository) {
        this.muscleGroupRepository = muscleGroupRepository;
        this.exerciseTypeRepository = exerciseTypeRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @GetMapping("/muscle-groups")
    public ResponseEntity<List<MuscleGroup>> getMuscleGroups() {
        return ResponseEntity.ok(muscleGroupRepository.findAll());
    }

    @GetMapping("/exercise-types")
    public ResponseEntity<List<ExerciseType>> getExerciseTypess() {
        return ResponseEntity.ok(exerciseTypeRepository.findAll());
    }

    @GetMapping("/equipments")
    public ResponseEntity<List<Equipment>> getEquipments() {
        return ResponseEntity.ok(equipmentRepository.findAll());
    }
}
