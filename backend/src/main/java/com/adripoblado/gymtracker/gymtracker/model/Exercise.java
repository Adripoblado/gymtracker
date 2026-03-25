package com.adripoblado.gymtracker.gymtracker.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    
    @ManyToMany
    @JoinTable(
        name = "exercise_muscle_group",
        joinColumns = @JoinColumn(name = "exercise_id"),
        inverseJoinColumns = @JoinColumn(name = "muscle_group_id")
    )
    private Set<MuscleGroup> muscleGroup = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "exercise_exercise_type",
        joinColumns = @JoinColumn(name = "exercise_id"),
        inverseJoinColumns = @JoinColumn(name = "exercise_type_id")
    )
    private Set<ExerciseType> exerciseType = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "exercise_equipment",
        joinColumns = @JoinColumn(name = "exercise_id"),
        inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipment = new HashSet<>();
    //private String videoUrl;
    private boolean isCustom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    public Exercise() {
    }

    public Exercise(String name, String description, boolean isCustom, User user) {
        this.name = name;
        this.description = description;
        this.isCustom = isCustom;
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<MuscleGroup> getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(Set<MuscleGroup> muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public Set<ExerciseType> getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(Set<ExerciseType> exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Set<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(Set<Equipment> equipment) {
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
