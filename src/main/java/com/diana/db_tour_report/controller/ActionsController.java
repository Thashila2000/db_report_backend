package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.ActionsRequest;
import com.diana.db_tour_report.dto.ActionsResponse;
import com.diana.db_tour_report.service.ActionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/actions")

public class ActionsController {

    private final ActionsService actionsService;

    @Autowired
    public ActionsController(ActionsService actionsService) {
        this.actionsService = actionsService;
    }

    // Save actions - Main endpoint for RemarksStep submission
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveActions(@Valid @RequestBody ActionsRequest request) {
        try {
            List<ActionsResponse> responses = actionsService.saveActions(request);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("message", "Actions saved successfully");
            responseMap.put("data", responses);
            responseMap.put("count", responses.size());

            return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to save actions: " + e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get action by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getActionById(@PathVariable Long id) {
        try {
            ActionsResponse response = actionsService.getActionById(id);

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

    // Get all actions
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllActions() {
        List<ActionsResponse> actions = actionsService.getAllActions();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", actions);
        responseMap.put("count", actions.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get actions by user name
    @GetMapping("/user/{userName}")
    public ResponseEntity<Map<String, Object>> getActionsByUserName(@PathVariable String userName) {
        List<ActionsResponse> actions = actionsService.getActionsByUserName(userName);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", actions);
        responseMap.put("count", actions.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get actions by user role
    @GetMapping("/role/{userRole}")
    public ResponseEntity<Map<String, Object>> getActionsByUserRole(@PathVariable String userRole) {
        List<ActionsResponse> actions = actionsService.getActionsByUserRole(userRole);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", actions);
        responseMap.put("count", actions.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get actions by fixed status
    @GetMapping("/fixed/{isFixed}")
    public ResponseEntity<Map<String, Object>> getActionsByFixedStatus(@PathVariable Boolean isFixed) {
        List<ActionsResponse> actions = actionsService.getActionsByFixedStatus(isFixed);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", actions);
        responseMap.put("count", actions.size());

        return ResponseEntity.ok(responseMap);
    }

    // Delete action
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteAction(@PathVariable Long id) {
        try {
            actionsService.deleteAction(id);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("message", "Action deleted successfully");

            return ResponseEntity.ok(responseMap);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
