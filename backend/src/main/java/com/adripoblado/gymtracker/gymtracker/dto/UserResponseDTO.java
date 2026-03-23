package com.adripoblado.gymtracker.gymtracker.dto;

public record UserResponseDTO(
    Long id,
    String username,
    String email,
    String role,
    BodyMetricsDTO bodyMetrics
) {
}
