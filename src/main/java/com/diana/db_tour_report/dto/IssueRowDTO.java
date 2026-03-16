package com.diana.db_tour_report.dto;

import jakarta.validation.constraints.*;

public class IssueRowDTO {

    private String id; // Frontend ID (i1, i2, random, etc.)

    // ✅ THE MASTER LINK: Connects this row to the specific report visit
    @NotBlank(message = "Report Group ID is required")
    private String reportGroupId;

    @NotBlank(message = "Issue type is required")
    @Size(max = 200, message = "Issue type cannot exceed 200 characters")
    private String issueType;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Security level is required")
    @Pattern(regexp = "N/A|Low|Medium|High", message = "Security must be N/A, Low, Medium, or High")
    private String security;

    @NotNull(message = "isFixed flag is required")
    private Boolean isFixed;

    // ✅ Metadata for the report
    private String userName;
    private String userRole;

    // --- Constructors ---
    public IssueRowDTO() {}

    // --- Getters and Setters ---
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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
}