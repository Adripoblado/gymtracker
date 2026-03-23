package com.adripoblado.gymtracker.gymtracker.dto;

public class WorkoutSetResponseDTO {

    private Long id;
    private int setNumber;
    private double weight;
    private int reps;

    public WorkoutSetResponseDTO() {
    }

    public WorkoutSetResponseDTO(Long id, int setNumber, double weight, int reps) {
        this.id = id;
        this.setNumber = setNumber;
        this.weight = weight;
        this.reps = reps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
