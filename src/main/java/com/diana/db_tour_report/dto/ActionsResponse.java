package com.diana.db_tour_report.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ActionsResponse {

    private Long id;

    // ✅ THE MASTER LINK: Crucial for the frontend to group data correctly
    private String reportGroupId;

    private String action;
    private String comment;
    private Boolean isFixed;
    private String userName;
    private String userRole;

    // ✅ Formats the DB time for the UI style: 2026.03.09 10.59
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime updatedAt;

    // --- Constructors ---
    public ActionsResponse() {}

    // --- Getters and Setters ---
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

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