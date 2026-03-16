package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.entity.User;
import com.diana.db_tour_report.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all regions for a user
    @GetMapping("/my-regions")
    public List<String> getMyRegions(@RequestParam String username) {
        return userService.getRegionsForUser(username);
    }

    // Get all areas for a user
    @GetMapping("/my-areas")
    public List<String> getMyAreas(@RequestParam String username) {
        return userService.getAreasForUser(username);
    }

    // Get user details
    @GetMapping("/{username}")
    public User getUserByName(@PathVariable String username) {
        return userService.getUserByName(username);
    }
}
