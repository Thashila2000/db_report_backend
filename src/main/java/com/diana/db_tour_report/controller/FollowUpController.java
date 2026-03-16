package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.FollowUpRequest;
import com.diana.db_tour_report.dto.FollowUpResponse;
import com.diana.db_tour_report.service.FollowUpService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow-up")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class FollowUpController {

    private final FollowUpService service;

    public FollowUpController(FollowUpService service) {
        this.service = service;
    }

    // Batch save follow-ups for one report visit
    @PostMapping
    public List<FollowUpResponse> saveFollowUps(@RequestBody FollowUpRequest request) {
        return service.saveFollowUps(request);
    }

    // FULL TABLE VIEW: Get all follow-ups for one specific report visit
    @GetMapping("/report/{reportGroupId}")
    public List<FollowUpResponse> getFollowUpsByReport(@PathVariable String reportGroupId) {
        return service.getFollowUpsByReportGroupId(reportGroupId);
    }

    // HISTORY VIEW: Get all follow-ups for a user, sorted by tour time
    @GetMapping("/user/{userName}")
    public List<FollowUpResponse> getUserFollowUpHistory(@PathVariable String userName) {
        return service.getSortedHistory(userName);
    }


}