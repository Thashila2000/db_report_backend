// AuthService.java
package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.LoginRequest;
import com.diana.db_tour_report.dto.LoginResponse;
import com.diana.db_tour_report.entity.User;
import com.diana.db_tour_report.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepo.findByNameAndPassword(request.getName(), request.getPassword())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        return new LoginResponse(user);
    }
}
