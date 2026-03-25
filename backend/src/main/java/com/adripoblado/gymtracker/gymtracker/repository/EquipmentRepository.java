package com.adripoblado.gymtracker.gymtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adripoblado.gymtracker.gymtracker.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

}
