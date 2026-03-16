package com.diana.db_tour_report.dto;

import jakarta.validation.constraints.NotBlank;

public class RemarksDTO {

    // ✅ THE MASTER LINK: Connects these remarks to the specific tour visit
    @NotBlank(message = "Report Group ID is required")
    private String reportGroupId;

    private String remarks;
    private String preparedBy;
    private String userName;
    private String userRole;

    public RemarksDTO() {
    }

    // --- Added Getter and Setter for the Link ---
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    // --- Existing Getters and Setters ---
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getPreparedBy() { return preparedBy; }
    public void setPreparedBy(String preparedBy) { this.preparedBy = preparedBy; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
}