package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.entity.ActionStaff;
import com.diana.db_tour_report.repository.ActionStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/action-staff")
@CrossOrigin(origins = "http://localhost:5173") // Added missing closing quote/brace check
public class ActionStaffController {

    @Autowired
    private ActionStaffRepository repository;

    @PostMapping("/bulk")
    public ResponseEntity<Map<String, Object>> saveBulkStaffActions(@RequestBody List<ActionStaff> staffActions) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ActionStaff> savedActions = repository.saveAll(staffActions);
            response.put("success", true);
            response.put("message", "Staff actions saved successfully");
            response.put("count", savedActions.size());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update this method in ActionStaffController.java
    @GetMapping("/bulk/{reportGroupId}")
    public ResponseEntity<List<ActionStaff>> getStaffByGroupId(@PathVariable String reportGroupId) {
        // Change findByReportGroupId -> findAllByReportGroupId
        List<ActionStaff> staffList = repository.findAllByReportGroupId(reportGroupId);
        return ResponseEntity.ok(staffList);
    }
} // Ensure this closing brace is present!