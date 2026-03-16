package com.diana.db_tour_report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class IssuesRequest {

    // ✅ THE MASTER LINK: Every batch of issues must have the generated ID
    @NotBlank(message = "Report Group ID is required")
    private String reportGroupId;

    @Valid
    @NotNull(message = "Rows are required")
    @Size(min = 1, message = "At least one issue row is required")
    private List<IssueRowDTO> rows;

    @NotBlank(message = "User name is required")
    @Size(max = 100, message = "User name cannot exceed 100 characters")
    private String userName;

    @NotBlank(message = "User role is required")
    @Size(max = 150, message = "User role cannot exceed 150 characters")
    private String userRole;

    // --- Constructors ---
    public IssuesRequest() {}

    // --- Getters and Setters ---
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public List<IssueRowDTO> getRows() { return rows; }
    public void setRows(List<IssueRowDTO> rows) { this.rows = rows; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
}