package com.diana.db_tour_report.dto;

import jakarta.validation.constraints.*;

/**
 * 📊 Represents a single row in the Sales Performance table.
 * Validated individually when sent as part of a batch list.
 */
public class SalesRowDTO {

    private String id; // Typically the frontend UUID or row index

    @NotBlank(message = "Category is required")
    @Size(max = 100)
    private String category;

    @NotBlank(message = "MTD Target is required")
    @Size(max = 50)
    private String mtdTarget;

    @NotBlank(message = "MTD Achievement is required")
    @Size(max = 50)
    private String mtdAchieved;

    // ✅ Added to match the Entity and Response requirements
    private String variance;

    @Size(max = 200)
    private String remarks;

    public SalesRowDTO() {}

    public SalesRowDTO(String id, String category, String mtdTarget, String mtdAchieved, String variance, String remarks) {
        this.id = id;
        this.category = category;
        this.mtdTarget = mtdTarget;
        this.mtdAchieved = mtdAchieved;
        this.variance = variance;
        this.remarks = remarks;
    }

    // --- Getters & Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getMtdTarget() { return mtdTarget; }
    public void setMtdTarget(String mtdTarget) { this.mtdTarget = mtdTarget; }

    public String getMtdAchieved() { return mtdAchieved; }
    public void setMtdAchieved(String mtdAchieved) { this.mtdAchieved = mtdAchieved; }

    public String getVariance() { return variance; }
    public void setVariance(String variance) { this.variance = variance; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}