package com.diana.db_tour_report.service;

import com.diana.db_tour_report.entity.User;
import com.diana.db_tour_report.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all regions for a user (split by comma)
    public List<String> getRegionsForUser(String username) {
        return userRepository.findByName(username)
                .map(user -> {
                    String regions = user.getRegion();
                    if (regions == null || regions.trim().isEmpty()) {
                        return new ArrayList<String>();
                    }
                    // Split by comma and trim whitespace
                    return Arrays.stream(regions.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                })
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    // Get all areas for a user (split by comma)
    public List<String> getAreasForUser(String username) {
        return userRepository.findByName(username)
                .map(user -> {
                    String areas = user.getArea();
                    if (areas == null || areas.trim().isEmpty()) {
                        return new ArrayList<String>();
                    }
                    // Split by comma and trim whitespace
                    return Arrays.stream(areas.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .collect(Collectors.toList());
                })
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    // Get user by username
    public User getUserByName(String username) {
        return userRepository.findByName(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    // Authenticate user
    public User authenticate(String username, String password) {
        return userRepository.findByNameAndPassword(username, password)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}
