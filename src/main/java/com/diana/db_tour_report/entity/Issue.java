package com.diana.db_tour_report.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "issues_identified")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ THE MASTER LINK: Connects this issue to the specific visit/tour
    @Column(name = "report_group_id", nullable = false, length = 150)
    private String reportGroupId;

    @Column(name = "issue_type", nullable = false, length = 200)
    private String issueType;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "security", nullable = false, length = 20)
    private String security; // N/A, Low, Medium, High

    @Column(name = "is_fixed", nullable = false)
    private Boolean isFixed;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "user_role", length = 150)
    private String userRole;

    @Column(name = "created_at", updatable = false)
    // ✅ Formats for the "Day-by-Day" UI: 2026.03.09 10.59
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime updatedAt;

    // --- Constructors ---
    public Issue() {}

    // --- Getters and Setters ---

    // ✅ New Getter/Setter for the ID link
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIssueType() { return issueType; }
    public void setIssueType(String issueType) { this.issueType = issueType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSecurity() { return security; }
    public void setSecurity(String security) { this.security = security; }

    public Boolean getIsFixed() { return isFixed; }
    public void setIsFixed(Boolean isFixed) { this.isFixed = isFixed; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // --- Lifecycle hooks ---
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}