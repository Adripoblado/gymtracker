package com.adripoblado.gymtracker.gymtracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.adripoblado.gymtracker.gymtracker.dto.WorkoutRequestDTO;
import com.adripoblado.gymtracker.gymtracker.dto.WorkoutResponseDTO;
import com.adripoblado.gymtracker.gymtracker.dto.WorkoutSetRequestDTO;
import com.adripoblado.gymtracker.gymtracker.mapper.WorkoutMapper;
import com.adripoblado.gymtracker.gymtracker.model.Exercise;
import com.adripoblado.gymtracker.gymtracker.model.User;
import com.adripoblado.gymtracker.gymtracker.model.Workout;
import com.adripoblado.gymtracker.gymtracker.model.WorkoutExercise;
import com.adripoblado.gymtracker.gymtracker.model.WorkoutSet;
import com.adripoblado.gymtracker.gymtracker.repository.ExerciseRepository;
import com.adripoblado.gymtracker.gymtracker.repository.WorkoutRepository;
import com.adripoblado.gymtracker.gymtracker.security.SecurityUtils;

import jakarta.transaction.Transactional;

@Service
public class WorkoutService {

    SecurityUtils securityUtils;
    ExerciseRepository exerciseRepository;
    WorkoutRepository workoutRepository;
    WorkoutMapper workoutMapper;

    public WorkoutService(SecurityUtils securityUtils, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository, WorkoutMapper workoutMapper) {
        this.securityUtils = securityUtils;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
    }

    @Transactional
    public String createWorkout(WorkoutRequestDTO request) {
        User user = securityUtils.getCurrentUser();
        Workout workout = new Workout(request.getName(), request.getStartTime(), request.getEndTime());
        workout.setUser(user);

        request.getExercises().forEach(e -> {
            Exercise exercise = exerciseRepository.findById(e.getExerciseId()).orElse(null);
            List<WorkoutSetRequestDTO> workoutSets = e.getSets();
            WorkoutExercise exerciseEntity = new WorkoutExercise(e.getOrderIndex(), workoutSets.size(), exercise);
            exerciseEntity.setWorkout(workout);
            workoutSets.forEach(s -> {
                WorkoutSet setEntity = new WorkoutSet(s.getSetNumber(), s.getReps(), s.getWeight(), s.getRpe());
                setEntity.setWorkoutExercise(exerciseEntity);
                exerciseEntity.getSets().add(setEntity);
            });
            workout.getExercises().add(exerciseEntity);
        });

        workoutRepository.save(workout);

        return "Workout created successfully";
    }

    @Transactional
    public List<WorkoutResponseDTO> getWorkoutsForCurrentUser() {
        User user = securityUtils.getCurrentUser();
        List<Workout> workouts = workoutRepository.findByUserId(user.getId());
        return workouts.stream().map(workoutMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public String deleteWorkout(Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId).orElseThrow(() -> new RuntimeException("Workout not found"));
        User user = securityUtils.getCurrentUser();

        if (!workout.getUser().equals(user)) {
            return "Unauthorized to delete this workout";
        }

        workoutRepository.deleteById(workoutId);
        return "Workout deleted successfully";
    }
}
