package com.diana.db_tour_report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class FollowUpRequest {

    // THE MASTER LINK: Connects this batch of follow-ups to the specific tour
    @NotBlank(message = "Report Group ID is required")
    private String reportGroupId;

    @NotBlank(message = "User name is required")
    private String userName;

    private String userRole;

    @Valid
    @NotEmpty(message = "At least one follow-up row is required")
    private List<FollowUpRowDTO> rows;

    public FollowUpRequest() {}

    // --- Getters and Setters ---
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public List<FollowUpRowDTO> getRows() { return rows; }
    public void setRows(List<FollowUpRowDTO> rows) { this.rows = rows; }
}