package com.diana.db_tour_report.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StockItemDTO {

    private String id; // Frontend ID (b1, b2, c1, etc.)

    // This connects the DTO to the specific tour report
    private String reportGroupId;

    // ADDED: Needed to group items (e.g., Beverages, Snacks) in the PDF
    private String categoryName;

    @NotBlank(message = "Item name is required")
    @Size(max = 200, message = "Item name cannot exceed 200 characters")
    private String itemName;

    @NotBlank(message = "Stock level is required")
    @Size(max = 50, message = "Stock level cannot exceed 50 characters")
    private String stockLevel;

    @NotBlank(message = "System stock is required")
    @Size(max = 50, message = "System stock cannot exceed 50 characters")
    private String systemStock;

    // ✅ ADDED: Metadata for the report
    private String userName;
    private String userRole;

    // ── Constructors ───────────────────────────────
    public StockItemDTO() {}

    // ── Getters and Setters ────────────────────────
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getStockLevel() { return stockLevel; }
    public void setStockLevel(String stockLevel) { this.stockLevel = stockLevel; }

    public String getSystemStock() { return systemStock; }
    public void setSystemStock(String systemStock) { this.systemStock = systemStock; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
}