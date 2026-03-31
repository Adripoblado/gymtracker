package com.adripoblado.gymtracker.gymtracker.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String jwtError = (String) request.getAttribute("jwt_error");
        
        response.setContentType("application/json");

        Map<String, Object> body = new HashMap<>();
        body.put("path", request.getServletPath());

        if ("EXPIRED".equals(jwtError)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            body.put("error", "Unauthorized");
            body.put("message", "Your session has expired or token is not valid.");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            body.put("status", HttpServletResponse.SC_NOT_FOUND);
            body.put("error", "Not found");
            body.put("message", "Requested resourced either doesn't exist or you don't have the needed permissions.");
        }
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
