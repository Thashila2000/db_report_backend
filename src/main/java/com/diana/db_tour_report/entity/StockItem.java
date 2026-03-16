package com.diana.db_tour_report.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "stock_items")
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ THE MASTER LINK: This connects multiple items to one report
    @Column(name = "report_group_id", nullable = false, length = 150)
    private String reportGroupId;

    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    @Column(name = "item_name", nullable = false, length = 200)
    private String itemName;

    @Column(name = "stock_level", nullable = false, length = 50)
    private String stockLevel;

    @Column(name = "system_stock", nullable = false, length = 50)
    private String systemStock;

    @Column(name = "variance", length = 50)
    private String variance;

    @Column(name = "category_comment", columnDefinition = "TEXT")
    private String categoryComment;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "user_role", length = 50)
    private String userRole;

    @Column(name = "created_at", updatable = false)
    // ✅ Formats your 2026-03-09 10:59:56... for the Frontend display
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime updatedAt;

    // ── Constructors ───────────────────────────────────────────────
    public StockItem() {
    }

    // Updated constructor to include reportGroupId
    public StockItem(String reportGroupId, String categoryName, String itemName, String stockLevel,
                     String systemStock, String variance, String categoryComment,
                     String userName, String userRole) {
        this.reportGroupId = reportGroupId;
        this.categoryName = categoryName;
        this.itemName = itemName;
        this.stockLevel = stockLevel;
        this.systemStock = systemStock;
        this.variance = variance;
        this.categoryComment = categoryComment;
        this.userName = userName;
        this.userRole = userRole;
    }

    // ── Getters and Setters ───────────────────────────────────────

    // ✅ New Getter and Setter for the link
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

    // ── Lifecycle hooks ───────────────────────────────────────────
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}