package com.diana.db_tour_report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

/**

 * Used when the user clicks 'Save' on the Sales Table in the React frontend.
 */
public class SalesPerformanceRequest {

    @Valid // ✅ Critical: Tells Spring to validate each SalesRowDTO inside the list
    @NotEmpty(message = "Sales data rows cannot be empty")
    private List<SalesRowDTO> rows;

    @NotBlank(message = "User name is required")
    private String userName;

    @NotBlank(message = "User role is required")
    private String userRole;

    // ✅ THE MASTER LINK: Connects these performance rows to the specific visit
    @NotBlank(message = "Report group ID is required")
    @Size(max = 150)
    private String reportGroupId;

    public SalesPerformanceRequest() {}

    // --- Getters & Setters ---
    public List<SalesRowDTO> getRows() { return rows; }
    public void setRows(List<SalesRowDTO> rows) { this.rows = rows; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }
}