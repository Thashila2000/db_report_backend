package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.MarketExecutionRequest;
import com.diana.db_tour_report.service.MarketExecutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/market-execution")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class MarketExecutionController {

    private final MarketExecutionService service;

    public MarketExecutionController(MarketExecutionService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submit(@RequestBody MarketExecutionRequest request) {
        try {
            service.submitFullReport(request);
            return ResponseEntity.ok("Report submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Submission failed: " + e.getMessage());
        }
    }
}