package com.adripoblado.gymtracker.gymtracker.dto;

public record RegisterRequestDTO(
        String username,
        String email,
        String password,
        String rolecode
) {
}