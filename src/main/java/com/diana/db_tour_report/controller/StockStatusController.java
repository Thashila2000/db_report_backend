package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.StockStatusRequest;
import com.diana.db_tour_report.dto.StockStatusResponse;
import com.diana.db_tour_report.service.StockStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stock-status")

public class StockStatusController {

    @Autowired
    private StockStatusService stockStatusService;

    // Save stock status data - Main endpoint for RemarksStep submission
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveStockStatus(@Valid @RequestBody StockStatusRequest request) {
        try {
            List<StockStatusResponse> responses = stockStatusService.saveStockStatus(request);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("message", "Stock Status data saved successfully");
            responseMap.put("data", responses);
            responseMap.put("count", responses.size());

            return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to save Stock Status: " + e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get stock item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStockItemById(@PathVariable Long id) {
        try {
            StockStatusResponse response = stockStatusService.getStockItemById(id);

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

    // Get all stock items
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStockItems() {
        List<StockStatusResponse> items = stockStatusService.getAllStockItems();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", items);
        responseMap.put("count", items.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get stock items by user name
    @GetMapping("/user/{userName}")
    public ResponseEntity<Map<String, Object>> getStockItemsByUserName(@PathVariable String userName) {
        List<StockStatusResponse> items = stockStatusService.getStockItemsByUserName(userName);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", items);
        responseMap.put("count", items.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get stock items by user role
    @GetMapping("/role/{userRole}")
    public ResponseEntity<Map<String, Object>> getStockItemsByUserRole(@PathVariable String userRole) {
        List<StockStatusResponse> items = stockStatusService.getStockItemsByUserRole(userRole);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", items);
        responseMap.put("count", items.size());

        return ResponseEntity.ok(responseMap);
    }

    // Get stock items by category name
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<Map<String, Object>> getStockItemsByCategoryName(@PathVariable String categoryName) {
        List<StockStatusResponse> items = stockStatusService.getStockItemsByCategoryName(categoryName);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);
        responseMap.put("data", items);
        responseMap.put("count", items.size());

        return ResponseEntity.ok(responseMap);
    }

    // Delete stock item
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStockItem(@PathVariable Long id) {
        try {
            stockStatusService.deleteStockItem(id);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("success", true);
            responseMap.put("message", "Stock item deleted successfully");

            return ResponseEntity.ok(responseMap);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
