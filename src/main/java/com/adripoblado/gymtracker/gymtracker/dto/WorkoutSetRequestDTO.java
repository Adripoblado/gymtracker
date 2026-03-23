package com.adripoblado.gymtracker.gymtracker.dto;

public class WorkoutSetRequestDTO {

    private int setNumber;
    private int reps;
    private double weight;
    private int rpe;

    public WorkoutSetRequestDTO() {
    }

    public WorkoutSetRequestDTO(int setNumber, int reps, double weight, int rpe) {
        this.setNumber = setNumber;
        this.reps = reps;
        this.weight = weight;
        this.rpe = rpe;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getRpe() {
        return rpe;
    }

    public void setRpe(int rpe) {
        this.rpe = rpe;
    }
}
