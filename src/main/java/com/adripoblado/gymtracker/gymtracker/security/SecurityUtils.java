package com.adripoblado.gymtracker.gymtracker.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.repository.UserRepository;

@Component
public class SecurityUtils {

    private final UserRepository userRepository;

    public SecurityUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
    // Si es anonymousUser o null, no hay nadie logueado correctamente
    if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
        return null;
    }

    // Normalmente el principal es un UserDetails (el username)
    String username = auth.getName(); 
    return userRepository.findByUsername(username).orElse(null);
}
}
