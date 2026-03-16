package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.DBProfileDTO;
import com.diana.db_tour_report.entity.DBProfile;
import com.diana.db_tour_report.service.DBProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/db-profile")
@CrossOrigin(origins = "http://localhost:5173") // ✅ Essential for Vite/React dev
public class DBProfileController {

    private final DBProfileService dbProfileService;

    public DBProfileController(DBProfileService dbProfileService) {
        this.dbProfileService = dbProfileService;
    }

    // --- CREATE & UPDATE ---

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProfile(@RequestBody DBProfileDTO dto) {
        try {
            DBProfile saved = dbProfileService.save(dto);
            return createResponse("DB Profile saved successfully", saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return createErrorResponse("Failed to save profile: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProfile(@PathVariable Long id, @RequestBody DBProfileDTO dto) {
        try {
            DBProfile updated = dbProfileService.update(id, dto);
            return createResponse("DB Profile updated successfully", updated, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // --- RETRIEVAL ---

    @GetMapping("/report/{reportGroupId}")
    public ResponseEntity<Map<String, Object>> getByReportGroupId(@PathVariable String reportGroupId) {
        return dbProfileService.findByReportGroupId(reportGroupId)
                .map(profile -> createResponse("Profile found", profile, HttpStatus.OK))
                .orElse(createErrorResponse("No profile found for this report", HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<Map<String, Object>> getByUser(@PathVariable String userName) {
        List<DBProfile> profiles = dbProfileService.findByUserName(userName);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", profiles);
        response.put("count", profiles.size());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        dbProfileService.deleteById(id);
        return createResponse("Deleted successfully", null, HttpStatus.OK);
    }

    // --- HELPER METHODS FOR CLEAN JSON RESPONSES ---

    private ResponseEntity<Map<String, Object>> createResponse(String message, Object data, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", message);
        if (data != null) map.put("data", data);
        return new ResponseEntity<>(map, status);
    }

    private ResponseEntity<Map<String, Object>> createErrorResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("message", message);
        return new ResponseEntity<>(map, status);
    }
}