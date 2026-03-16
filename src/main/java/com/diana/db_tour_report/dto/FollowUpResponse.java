package com.diana.db_tour_report.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class FollowUpResponse {

    private Long id;

    // THE MASTER LINK: Connects this response to the specific visit ID
    private String reportGroupId;

    private String action;
    private String responsible;
    private String deadline;
    private String userName;
    private String userRole;

    // Formats for your "Day-by-Day" sorting view: 2026.03.09 15.29
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime createdAt;

    // --- Constructors ---
    public FollowUpResponse() {}

    public FollowUpResponse(Long id, String reportGroupId, String action, String responsible,
                            String deadline, String userName, String userRole, LocalDateTime createdAt) {
        this.id = id;
        this.reportGroupId = reportGroupId;
        this.action = action;
        this.responsible = responsible;
        this.deadline = deadline;
        this.userName = userName;
        this.userRole = userRole;
        this.createdAt = createdAt;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getResponsible() { return responsible; }
    public void setResponsible(String responsible) { this.responsible = responsible; }

    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}