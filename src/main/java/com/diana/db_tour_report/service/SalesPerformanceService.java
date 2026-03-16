package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.SalesPerformanceRequest;
import com.diana.db_tour_report.dto.SalesPerformanceResponse;
import java.util.List;

public interface SalesPerformanceService {

    // Save sales performance data (all rows at once)
    List<SalesPerformanceResponse> saveSalesPerformance(SalesPerformanceRequest request);

    // ✅ Get ALL sales performance rows by report group ID (for reports)
    List<SalesPerformanceResponse> getSalesPerformanceByReportGroupId(String reportGroupId);

    // Get sales performance by ID
    SalesPerformanceResponse getSalesPerformanceById(Long id);

    // Get all sales performance records
    List<SalesPerformanceResponse> getAllSalesPerformance();

    // Get sales performance by user name
    List<SalesPerformanceResponse> getSalesPerformanceByUserName(String userName);

    // Get sales performance by user role
    List<SalesPerformanceResponse> getSalesPerformanceByUserRole(String userRole);

    // Delete sales performance record
    void deleteSalesPerformance(Long id);
}