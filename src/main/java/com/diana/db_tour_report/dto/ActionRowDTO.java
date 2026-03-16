package com.diana.db_tour_report.dto;

import jakarta.validation.constraints.*;

public class ActionRowDTO {

    private String id; // Frontend ID (e.g., a1, a2)

    // ✅ THE MASTER LINK: Connects this action to the specific report visit
    @NotBlank(message = "Report Group ID is required")
    private String reportGroupId;

    @NotBlank(message = "Action is required")
    private String action;

    @NotBlank(message = "Comment is required")
    private String comment;

    @NotNull(message = "isFixed flag is required")
    private Boolean isFixed;

    // ✅ Metadata for the report tracking
    private String userName;
    private String userRole;

    // --- Constructors ---
    public ActionRowDTO() {}

    // --- Getters and Setters ---
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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
}