package com.diana.db_tour_report.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "sales_performance", indexes = {
        @Index(name = "idx_report_group", columnList = "report_group_id")
})
public class SalesPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    @Column(name = "mtd_target", nullable = false, length = 50)
    private String mtdTarget;

    @Column(name = "mtd_achieved", nullable = false, length = 50)
    private String mtdAchieved;

    @Column(name = "variance", length = 50)
    private String variance;

    @Column(name = "remarks", length = 200)
    private String remarks;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "user_role", length = 50)
    private String userRole;

    @Column(name = "report_group_id", length = 150)
    private String reportGroupId;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime updatedAt;

    public SalesPerformance() {}

    // ---------------- Lifecycle Hooks ----------------
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ---------------- Getters & Setters ----------------
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