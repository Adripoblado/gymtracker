package com.adripoblado.gymtracker.gymtracker.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class BodyMetrics {

    private int age;
    private int weight;
    private int height;
    private int chest;
    private int shoulders;
    private int waist;
    private int hips;
    private int biceps;
    private int forearm;
    private int thigh;
    private int calf;
    private int neck;
    private int bodyFatPercentage;
    private int muscleMassPercentage;
    private int waterPercentage;
    private int boneMassPercentage;
    private int visceralFatLevel;
    private int metabolicAge;
    private int basalMetabolicRate;
    private int bodyMassIndex;

    public BodyMetrics() {
    }

    public BodyMetrics(int age, int weight, int height) {
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getChest() {
        return chest;
    }

    public void setChest(int chest) {
        this.chest = chest;
    }

    public int getShoulders() {
        return shoulders;
    }

    public void setShoulders(int shoulders) {
        this.shoulders = shoulders;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getHips() {
        return hips;
    }

    public void setHips(int hips) {
        this.hips = hips;
    }

    public int getBiceps() {
        return biceps;
    }

    public void setBiceps(int biceps) {
        this.biceps = biceps;
    }

    public int getForearm() {
        return forearm;
    }

    public void setForearm(int forearm) {
        this.forearm = forearm;
    }

    public int getThigh() {
        return thigh;
    }

    public void setThigh(int thigh) {
        this.thigh = thigh;
    }

    public int getCalf() {
        return calf;
    }

    public void setCalf(int calf) {
        this.calf = calf;
    }

    public int getNeck() {
        return neck;
    }

    public void setNeck(int neck) {
        this.neck = neck;
    }

    public int getBodyFatPercentage() {
        return bodyFatPercentage;
    }

    public void setBodyFatPercentage(int bodyFatPercentage) {
        this.bodyFatPercentage = bodyFatPercentage;
    }

    public int getMuscleMassPercentage() {
        return muscleMassPercentage;
    }

    public void setMuscleMassPercentage(int muscleMassPercentage) {
        this.muscleMassPercentage = muscleMassPercentage;
    }

    public int getWaterPercentage() {
        return waterPercentage;
    }

    public void setWaterPercentage(int waterPercentage) {
        this.waterPercentage = waterPercentage;
    }

    public int getBoneMassPercentage() {
        return boneMassPercentage;
    }

    public void setBoneMassPercentage(int boneMassPercentage) {
        this.boneMassPercentage = boneMassPercentage;
    }

    public int getVisceralFatLevel() {
        return visceralFatLevel;
    }

    public void setVisceralFatLevel(int visceralFatLevel) {
        this.visceralFatLevel = visceralFatLevel;
    }

    public int getMetabolicAge() {
        return metabolicAge;
    }

    public void setMetabolicAge(int metabolicAge) {
        this.metabolicAge = metabolicAge;
    }

    public int getBasalMetabolicRate() {
        return basalMetabolicRate;
    }

    public void setBasalMetabolicRate(int basalMetabolicRate) {
        this.basalMetabolicRate = basalMetabolicRate;
    }

    public int getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(int bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }

    
}
