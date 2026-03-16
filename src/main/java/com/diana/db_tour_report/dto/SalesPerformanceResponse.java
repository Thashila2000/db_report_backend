package com.diana.db_tour_report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class SalesPerformanceResponse {

    private Long id;
    private String category;
    private String mtdTarget;
    private String mtdAchieved;
    private String variance;
    private String remarks;
    private String userName;
    private String userRole;


    private String reportGroupId;

    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime updatedAt;

    public SalesPerformanceResponse() {}

    public SalesPerformanceResponse(Long id, String category, String mtdTarget, String mtdAchieved,
                                    String variance, String remarks, String userName, String userRole,
                                    String reportGroupId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.category = category;
        this.mtdTarget = mtdTarget;
        this.mtdAchieved = mtdAchieved;
        this.variance = variance;
        this.remarks = remarks;
        this.userName = userName;
        this.userRole = userRole;
        this.reportGroupId = reportGroupId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getMtdTarget() { return mtdTarget; }
    public void setMtdTarget(String mtdTarget) { this.mtdTarget = mtdTarget; }

    public String getMtdAchieved() { return mtdAchieved; }
    public void setMtdAchieved(String mtdAchieved) { this.mtdAchieved = mtdAchieved; }

    public String getVariance() { return variance; }
    public void setVariance(String variance) { this.variance = variance; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}