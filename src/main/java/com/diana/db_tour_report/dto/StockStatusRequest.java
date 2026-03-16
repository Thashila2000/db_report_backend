package com.diana.db_tour_report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public class StockStatusRequest {

    // THE MASTER LINK: Every request must now carry the generated ID
    @NotBlank(message = "Report Group ID is required")
    private String reportGroupId;

    @Valid
    @NotNull(message = "Categories are required")
    @Size(min = 1, message = "At least one category is required")
    private List<StockCategoryDTO> categories;

    @NotBlank(message = "User name is required")
    @Size(max = 100, message = "User name cannot exceed 100 characters")
    private String userName;

    @NotBlank(message = "User role is required")
    @Size(max = 50, message = "User role cannot exceed 50 characters")
    private String userRole;

    // ✅ ADDED: To help with the "Day-by-Day" sorting display
    private String visitDate;

    // ── Constructors ───────────────────────────────
    public StockStatusRequest() {}

    // ── Getters and Setters ────────────────────────
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public List<StockCategoryDTO> getCategories() { return categories; }
    public void setCategories(List<StockCategoryDTO> categories) { this.categories = categories; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public String getVisitDate() { return visitDate; }
    public void setVisitDate(String visitDate) { this.visitDate = visitDate; }
}