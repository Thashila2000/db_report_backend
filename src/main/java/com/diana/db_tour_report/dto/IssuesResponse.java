package com.diana.db_tour_report.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class IssuesResponse {

    private Long id;


    private String reportGroupId;

    private String issueType;
    private String description;
    private String security;
    private Boolean isFixed;
    private String userName;
    private String userRole;

    // ✅ Formats the DB time (10:59:56.426...) into your preferred UI style
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime updatedAt;

    // --- Constructors ---
    public IssuesResponse() {}

    // --- Getters and Setters ---
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
}