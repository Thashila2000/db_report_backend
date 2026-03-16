package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.IssuesRequest;
import com.diana.db_tour_report.dto.IssuesResponse;
import com.diana.db_tour_report.service.IssuesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/issues")

public class IssuesController {

    @Autowired
    private IssuesService issuesService;

    // Save issues - Main endpoint for RemarksStep submission
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveIssues(@Valid @RequestBody IssuesRequest request) {
        try {
            List<IssuesResponse> responses = issuesService.saveIssues(request);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("message", "Issues saved successfully");
            responseMap.put("data", responses);
            responseMap.put("count", responses.size());

            return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to save issues: " + e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get issue by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getIssueById(@PathVariable Long id) {
        try {
            IssuesResponse response = issuesService.getIssueById(id);

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

    // Get all issues
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllIssues() {
        List<IssuesResponse> issues = issuesService.getAllIssues();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", issues);
        responseMap.put("count", issues.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get issues by user name
    @GetMapping("/user/{userName}")
    public ResponseEntity<Map<String, Object>> getIssuesByUserName(@PathVariable String userName) {
        List<IssuesResponse> issues = issuesService.getIssuesByUserName(userName);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", issues);
        responseMap.put("count", issues.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get issues by user role
    @GetMapping("/role/{userRole}")
    public ResponseEntity<Map<String, Object>> getIssuesByUserRole(@PathVariable String userRole) {
        List<IssuesResponse> issues = issuesService.getIssuesByUserRole(userRole);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", issues);
        responseMap.put("count", issues.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get issues by security level
    @GetMapping("/security/{security}")
    public ResponseEntity<Map<String, Object>> getIssuesBySecurity(@PathVariable String security) {
        List<IssuesResponse> issues = issuesService.getIssuesBySecurity(security);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", issues);
        responseMap.put("count", issues.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get issues by fixed status
    @GetMapping("/fixed/{isFixed}")
    public ResponseEntity<Map<String, Object>> getIssuesByFixedStatus(@PathVariable Boolean isFixed) {
        List<IssuesResponse> issues = issuesService.getIssuesByFixedStatus(isFixed);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", issues);
        responseMap.put("count", issues.size());

        return ResponseEntity.ok(responseMap);
    }

    // Delete issue
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteIssue(@PathVariable Long id) {
        try {
            issuesService.deleteIssue(id);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("message", "Issue deleted successfully");

            return ResponseEntity.ok(responseMap);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
