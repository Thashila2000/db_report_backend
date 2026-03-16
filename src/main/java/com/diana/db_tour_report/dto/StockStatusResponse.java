package com.diana.db_tour_report.dto;

import java.time.LocalDateTime;

public class StockStatusResponse {

    private Long id;

    // 🟢 ADDED: Master Link for the Response
    private String reportGroupId;

    private String categoryName;
    private String itemName;
    private String stockLevel;
    private String systemStock;
    private String variance;
    private String categoryComment;
    private String userName;
    private String userRole;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ── Constructors ───────────────────────────────
    public StockStatusResponse() {}

    public StockStatusResponse(Long id, String reportGroupId, String categoryName, String itemName,
                               String stockLevel, String systemStock, String variance,
                               String categoryComment, String userName, String userRole,
                               LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.reportGroupId = reportGroupId; // 🟢 Updated Constructor
        this.categoryName = categoryName;
        this.itemName = itemName;
        this.stockLevel = stockLevel;
        this.systemStock = systemStock;
        this.variance = variance;
        this.categoryComment = categoryComment;
        this.userName = userName;
        this.userRole = userRole;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ── Getters and Setters ────────────────────────

    // 🟢 ADDED: Getter and Setter for reportGroupId
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getStockLevel() { return stockLevel; }
    public void setStockLevel(String stockLevel) { this.stockLevel = stockLevel; }

    public String getSystemStock() { return systemStock; }
    public void setSystemStock(String systemStock) { this.systemStock = systemStock; }

    public String getVariance() { return variance; }
    public void setVariance(String variance) { this.variance = variance; }

    public String getCategoryComment() { return categoryComment; }
    public void setCategoryComment(String categoryComment) { this.categoryComment = categoryComment; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}