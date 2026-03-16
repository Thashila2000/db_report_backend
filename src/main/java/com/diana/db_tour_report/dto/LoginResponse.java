package com.diana.db_tour_report.dto;

import com.diana.db_tour_report.entity.User;
import java.util.Arrays;
import java.util.List;

public class LoginResponse {
    private Long id;
    private String name;
    private String role;
    private String region;
    private List<String> areas;  // multiple areas

    public LoginResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
        this.region = user.getRegion();
        // Split the comma-separated string into a List<String>
        this.areas = Arrays.asList(user.getArea().split(",\\s*"));
    }

    // getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getRegion() { return region; }
    public List<String> getAreas() { return areas; }
}
