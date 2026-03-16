package com.diana.db_tour_report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public class ActionsRequest {

    // ✅ THE MASTER LINK: Anchors this batch of actions to the specific tour
    @NotBlank(message = "Report Group ID is required")
    private String reportGroupId;

    @Valid
    @NotNull(message = "Rows are required")
    @Size(min = 1, message = "At least one action row is required")
    private List<ActionRowDTO> rows;

    @NotBlank(message = "User name is required")
    @Size(max = 100, message = "User name cannot exceed 100 characters")
    private String userName;

    @NotBlank(message = "User role is required")
    @Size(max = 150, message = "User role cannot exceed 150 characters")
    private String userRole;

    // Constructors
    public ActionsRequest() {}

    // Getters and Setters
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public List<ActionRowDTO> getRows() { return rows; }
    public void setRows(List<ActionRowDTO> rows) { this.rows = rows; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
}