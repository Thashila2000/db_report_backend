package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.*;
import com.diana.db_tour_report.entity.SalesPerformance;
import com.diana.db_tour_report.repository.SalesPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // If one row fails to save, none of them are saved
public class SalesPerformanceServiceImpl implements SalesPerformanceService {

    @Autowired
    private SalesPerformanceRepository salesPerformanceRepository;

    @Override
    public List<SalesPerformanceResponse> saveSalesPerformance(SalesPerformanceRequest request) {
        // 1.  Delete existing rows for this visit before saving new ones
        // This prevents duplicate categories if the user clicks "Save" multiple times.
        salesPerformanceRepository.deleteByReportGroupId(request.getReportGroupId());

        // 2. Map and Save
        List<SalesPerformance> entities = request.getRows().stream()
                .map(row -> convertToEntity(row, request.getUserName(), request.getUserRole(), request.getReportGroupId()))
                .collect(Collectors.toList());

        List<SalesPerformance> savedRecords = salesPerformanceRepository.saveAll(entities);

        return savedRecords.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // --- Search Methods ---

    @Override
    public List<SalesPerformanceResponse> getSalesPerformanceByReportGroupId(String reportGroupId) {
        return salesPerformanceRepository.findAllByReportGroupId(reportGroupId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SalesPerformanceResponse getSalesPerformanceById(Long id) {
        SalesPerformance entity = salesPerformanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales record not found with id: " + id));
        return convertToResponse(entity);
    }

    @Override
    public List<SalesPerformanceResponse> getAllSalesPerformance() {
        return salesPerformanceRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesPerformanceResponse> getSalesPerformanceByUserName(String userName) {
        return salesPerformanceRepository.findByUserName(userName).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesPerformanceResponse> getSalesPerformanceByUserRole(String userRole) {
        return salesPerformanceRepository.findByUserRole(userRole).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSalesPerformance(Long id) {
        if (!salesPerformanceRepository.existsById(id)) {
            throw new RuntimeException("Record not found: " + id);
        }
        salesPerformanceRepository.deleteById(id);
    }

    // --- Helper Methods: Intelligence Layer ---

    private String calculateVariance(String mtdTarget, String mtdAchieved) {
        try {
            double target = Double.parseDouble(mtdTarget);
            double achieved = Double.parseDouble(mtdAchieved);
            double variance = achieved - target;
            return String.format("%s%.2f", variance >= 0 ? "+" : "", variance);
        } catch (Exception e) { return "0.00"; }
    }

    private String calculateRemarks(String mtdTarget, String mtdAchieved) {
        try {
            double target = Double.parseDouble(mtdTarget);
            double achieved = Double.parseDouble(mtdAchieved);
            if (target == 0) return achieved > 0 ? "🎯 Bonus Sales" : "N/A";

            double pct = (achieved / target) * 100;
            if (pct >= 100) return "🎯 Over Target";
            if (pct >= 95)  return "✅ On Track";
            if (pct >= 85)  return "🔶 Needs Effort";
            return "🔴 Critical";
        } catch (Exception e) { return "Pending"; }
    }

    private SalesPerformance convertToEntity(SalesRowDTO row, String userName, String userRole, String reportGroupId) {
        SalesPerformance entity = new SalesPerformance();
        entity.setCategory(row.getCategory());
        entity.setMtdTarget(row.getMtdTarget());
        entity.setMtdAchieved(row.getMtdAchieved());
        entity.setVariance(calculateVariance(row.getMtdTarget(), row.getMtdAchieved()));
        entity.setRemarks(calculateRemarks(row.getMtdTarget(), row.getMtdAchieved()));
        entity.setUserName(userName);
        entity.setUserRole(userRole);
        entity.setReportGroupId(reportGroupId);
        return entity;
    }

    private SalesPerformanceResponse convertToResponse(SalesPerformance entity) {
        return new SalesPerformanceResponse(
                entity.getId(), entity.getCategory(), entity.getMtdTarget(), entity.getMtdAchieved(),
                entity.getVariance(), entity.getRemarks(), entity.getUserName(), entity.getUserRole(),
                entity.getReportGroupId(), entity.getCreatedAt(), entity.getUpdatedAt()
        );
    }
}