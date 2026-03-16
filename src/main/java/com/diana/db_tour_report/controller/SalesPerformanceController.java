package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.SalesPerformanceRequest;
import com.diana.db_tour_report.dto.SalesPerformanceResponse;
import com.diana.db_tour_report.service.SalesPerformanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales-performance")

public class SalesPerformanceController {

    @Autowired
    private SalesPerformanceService salesPerformanceService;

    // Save sales performance data - Main endpoint for final form submission
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveSalesPerformance(@Valid @RequestBody SalesPerformanceRequest request) {
        try {
            List<SalesPerformanceResponse> responses = salesPerformanceService.saveSalesPerformance(request);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("message", "Sales Performance data saved successfully");
            responseMap.put("data", responses);
            responseMap.put("count", responses.size());

            return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to save Sales Performance: " + e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get sales performance by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getSalesPerformanceById(@PathVariable Long id) {
        try {
            SalesPerformanceResponse response = salesPerformanceService.getSalesPerformanceById(id);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("data", response);

            return ResponseEntity.ok(responseMap);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    // Get all sales performance records
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllSalesPerformance() {
        List<SalesPerformanceResponse> records = salesPerformanceService.getAllSalesPerformance();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", records);
        responseMap.put("count", records.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get sales performance by user name
    @GetMapping("/user/{userName}")
    public ResponseEntity<Map<String, Object>> getSalesPerformanceByUserName(@PathVariable String userName) {
        List<SalesPerformanceResponse> records = salesPerformanceService.getSalesPerformanceByUserName(userName);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", records);
        responseMap.put("count", records.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get sales performance by user role
    @GetMapping("/role/{userRole}")
    public ResponseEntity<Map<String, Object>> getSalesPerformanceByUserRole(@PathVariable String userRole) {
        List<SalesPerformanceResponse> records = salesPerformanceService.getSalesPerformanceByUserRole(userRole);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", records);
        responseMap.put("count", records.size());

        return ResponseEntity.ok(responseMap);
    }

    // Delete sales performance record
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteSalesPerformance(@PathVariable Long id) {
        try {
            salesPerformanceService.deleteSalesPerformance(id);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("message", "Sales Performance record deleted successfully");

            return ResponseEntity.ok(responseMap);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
