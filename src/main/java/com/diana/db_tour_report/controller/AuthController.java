// AuthController.java
package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.LoginRequest;
import com.diana.db_tour_report.dto.LoginResponse;
import com.diana.db_tour_report.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
