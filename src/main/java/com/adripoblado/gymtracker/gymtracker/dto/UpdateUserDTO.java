package com.adripoblado.gymtracker.gymtracker.dto;

import org.hibernate.sql.Update;

import jakarta.validation.Valid;

public class UpdateUserDTO {

    private String username;
    private String name, surname;
    private String email;
    private String password;
    private String role;

    @Valid
    private BodyMetricsDTO bodyMetrics;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(String username, String name, String surname, String email, String password, String role, BodyMetricsDTO bodyMetrics) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.bodyMetrics = bodyMetrics;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BodyMetricsDTO getBodyMetrics() {
        return bodyMetrics;
    }

    public void setBodyMetrics(BodyMetricsDTO bodyMetrics) {
        this.bodyMetrics = bodyMetrics;
    }

}
