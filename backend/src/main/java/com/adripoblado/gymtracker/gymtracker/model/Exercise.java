package com.adripoblado.gymtracker.gymtracker.model;

import java.util.List;

import com.adripoblado.gymtracker.gymtracker.dto.CreateExerciseDTO;
import com.adripoblado.gymtracker.gymtracker.model.enums.Equipment;
import com.adripoblado.gymtracker.gymtracker.model.enums.ExerciseType;
import com.adripoblado.gymtracker.gymtracker.model.enums.MuscleGroup;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private List<MuscleGroup> muscleGroup;
    private List<ExerciseType> exerciseType;
    private List<Equipment> equipment;
    //private String videoUrl;
    private boolean isCustom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    public Exercise() {
    }

    public Exercise(String name, List<MuscleGroup> muscleGroup, List<ExerciseType> exerciseType, List<Equipment> equipment, boolean isCustom, User user) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.exerciseType = exerciseType;
        this.equipment = equipment;
        this.isCustom = isCustom;
        this.user = user;
    }

        public Exercise(CreateExerciseDTO request, User user) {
        this.name = request.name();
        this.muscleGroup = request.muscleGroup();
        this.exerciseType = request.exerciseType();
        this.equipment = request.equipment();
        if (user != null) {
            this.isCustom = true;
            this.user = user;
        } else {
            this.isCustom = false;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MuscleGroup> getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(List<MuscleGroup> muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public List<ExerciseType> getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(List<ExerciseType> exerciseType) {
        this.exerciseType = exerciseType;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean isCustom) {
        this.isCustom = isCustom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User creator) {
        this.user = creator;
    }

    @Override
    public String toString() { 
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", muscleGroup=" + muscleGroup +
                ", exerciseType=" + exerciseType +
                ", equipment=" + equipment +
                ", isCustom=" + isCustom +
                ", creator=" + (user != null ? user.getUsername() : "null") +
                '}';
    }
}
