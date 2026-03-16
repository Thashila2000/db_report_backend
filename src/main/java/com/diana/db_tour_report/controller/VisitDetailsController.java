package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.VisitDetailsDTO;
import com.diana.db_tour_report.entity.VisitDetails;
import com.diana.db_tour_report.service.VisitDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visit-details")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true") // ✅ Allow React Frontend
public class VisitDetailsController {

    private final VisitDetailsService visitService;

    public VisitDetailsController(VisitDetailsService visitService) {
        this.visitService = visitService;
    }

    // ✅ CREATE: Starts a new tour report entry
    @PostMapping
    public VisitDetails saveVisitDetails(@RequestBody VisitDetailsDTO dto) {
        return visitService.saveVisitDetails(dto);
    }

    // ✅ READ: Fetch history for the user dashboard (Sorted by newest first)
    @GetMapping("/user/{userName}")
    public List<VisitDetails> getUserVisitHistory(@PathVariable String userName) {
        return visitService.findByUserNameSorted(userName);
    }

    // ✅ READ: Fetch specific header by its Group ID (for viewing full report)
    @GetMapping("/report/{reportGroupId}")
    public VisitDetails getByGroupId(@PathVariable String reportGroupId) {
        return visitService.findByReportGroupId(reportGroupId)
                .orElseThrow(() -> new RuntimeException("Visit not found for ID: " + reportGroupId));
    }

}