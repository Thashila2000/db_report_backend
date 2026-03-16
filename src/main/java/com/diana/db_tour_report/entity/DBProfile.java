package com.diana.db_tour_report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "db_profile", indexes = {
        @Index(name = "idx_report_group", columnList = "report_group_id")
})
public class DBProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Information
    @Column(name = "db_owner_contact", length = 50)
    private String dbOwnerContact;

    @Column(name = "coverage_area", length = 100)
    private String coverageArea;

    @Column(name = "route_strength", length = 100)
    private String routeStrength;

    @Column(name = "sales_team", length = 100)
    private String salesTeam;

    @Column(name = "vehicle_availability", length = 100)
    private String vehicleAvailability;

    @Column(name = "log_book_update", length = 100)
    private String logBookUpdate;

    // Territory Route Map
    @Column(name = "territory_route_map", length = 10)
    private String territoryRouteMap;  // "yes" or "no"

    @Column(name = "route_map_image", columnDefinition = "LONGTEXT")
    private String routeMapImage;      // Base64 encoded

    // Route Plan
    @Column(name = "route_plan", length = 10)
    private String routePlan;          // "yes" or "no"

    @Column(name = "route_plan_image", columnDefinition = "LONGTEXT")
    private String routePlanImage;     // Base64 encoded

    // Financial Information
    @Column(name = "credit_bill_count", length = 50)
    private String creditBillCount;

    @Column(name = "credit_bill_total", length = 50)
    private String creditBillTotal;

    @Column(name = "cheque_count", length = 50)
    private String chequeCount;

    @Column(name = "cheque_total", length = 50)
    private String chequeTotal;

    @Column(name = "cash_total", length = 50)
    private String cashTotal;

    // Progress & SKU Updates
    @Column(name = "progress_sheet_update", columnDefinition = "TEXT")
    private String progressSheetUpdate;

    @Column(name = "sku_sales_update", length = 10)
    private String skuSalesUpdate;  // "low" or "high"

    @Column(name = "sku_sales_comment", columnDefinition = "TEXT")
    private String skuSalesComment;

    // Store Information
    @Column(name = "store_length", length = 50)
    private String storeLength;

    @Column(name = "store_width", length = 50)
    private String storeWidth;

    @Column(name = "store_condition", length = 100)
    private String storeCondition;

    @Column(name = "market_return_condition", length = 200)
    private String marketReturnCondition;

    @Column(name = "table_count", length = 50)
    private String tableCount;

    @Column(name = "chair_count", length = 50)
    private String chairCount;

    @Column(name = "store_comments", columnDefinition = "TEXT")
    private String storeComments;

    // User Tracking
    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "report_group_id", length = 150)
    private String reportGroupId;

    // Timestamps
    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm") //Displays as 2026.03.09 16.00
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public DBProfile() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDbOwnerContact() {
        return dbOwnerContact;
    }

    public void setDbOwnerContact(String dbOwnerContact) {
        this.dbOwnerContact = dbOwnerContact;
    }

    public String getCoverageArea() {
        return coverageArea;
    }

    public void setCoverageArea(String coverageArea) {
        this.coverageArea = coverageArea;
    }

    public String getRouteStrength() {
        return routeStrength;
    }

    public void setRouteStrength(String routeStrength) {
        this.routeStrength = routeStrength;
    }

    public String getSalesTeam() {
        return salesTeam;
    }

    public void setSalesTeam(String salesTeam) {
        this.salesTeam = salesTeam;
    }

    public String getVehicleAvailability() {
        return vehicleAvailability;
    }

    public void setVehicleAvailability(String vehicleAvailability) {
        this.vehicleAvailability = vehicleAvailability;
    }

    public String getLogBookUpdate() {
        return logBookUpdate;
    }

    public void setLogBookUpdate(String logBookUpdate) {
        this.logBookUpdate = logBookUpdate;
    }

    public String getTerritoryRouteMap() {
        return territoryRouteMap;
    }

    public void setTerritoryRouteMap(String territoryRouteMap) {
        this.territoryRouteMap = territoryRouteMap;
    }

    public String getRouteMapImage() {
        return routeMapImage;
    }

    public void setRouteMapImage(String routeMapImage) {
        this.routeMapImage = routeMapImage;
    }

    public String getRoutePlan() {
        return routePlan;
    }

    public void setRoutePlan(String routePlan) {
        this.routePlan = routePlan;
    }

    public String getRoutePlanImage() {
        return routePlanImage;
    }

    public void setRoutePlanImage(String routePlanImage) {
        this.routePlanImage = routePlanImage;
    }

    public String getCreditBillCount() {
        return creditBillCount;
    }

    public void setCreditBillCount(String creditBillCount) {
        this.creditBillCount = creditBillCount;
    }

    public String getCreditBillTotal() {
        return creditBillTotal;
    }

    public void setCreditBillTotal(String creditBillTotal) {
        this.creditBillTotal = creditBillTotal;
    }

    public String getChequeCount() {
        return chequeCount;
    }

    public void setChequeCount(String chequeCount) {
        this.chequeCount = chequeCount;
    }

    public String getChequeTotal() {
        return chequeTotal;
    }

    public void setChequeTotal(String chequeTotal) {
        this.chequeTotal = chequeTotal;
    }

    public String getCashTotal() {
        return cashTotal;
    }

    public void setCashTotal(String cashTotal) {
        this.cashTotal = cashTotal;
    }

    public String getProgressSheetUpdate() {
        return progressSheetUpdate;
    }

    public void setProgressSheetUpdate(String progressSheetUpdate) {
        this.progressSheetUpdate = progressSheetUpdate;
    }

    public String getSkuSalesUpdate() {
        return skuSalesUpdate;
    }

    public void setSkuSalesUpdate(String skuSalesUpdate) {
        this.skuSalesUpdate = skuSalesUpdate;
    }

    public String getSkuSalesComment() {
        return skuSalesComment;
    }

    public void setSkuSalesComment(String skuSalesComment) {
        this.skuSalesComment = skuSalesComment;
    }

    public String getStoreLength() {
        return storeLength;
    }

    public void setStoreLength(String storeLength) {
        this.storeLength = storeLength;
    }

    public String getStoreWidth() {
        return storeWidth;
    }

    public void setStoreWidth(String storeWidth) {
        this.storeWidth = storeWidth;
    }

    public String getStoreCondition() {
        return storeCondition;
    }

    public void setStoreCondition(String storeCondition) {
        this.storeCondition = storeCondition;
    }

    public String getMarketReturnCondition() {
        return marketReturnCondition;
    }

    public void setMarketReturnCondition(String marketReturnCondition) {
        this.marketReturnCondition = marketReturnCondition;
    }

    public String getTableCount() {
        return tableCount;
    }

    public void setTableCount(String tableCount) {
        this.tableCount = tableCount;
    }

    public String getChairCount() {
        return chairCount;
    }

    public void setChairCount(String chairCount) {
        this.chairCount = chairCount;
    }

    public String getStoreComments() {
        return storeComments;
    }

    public void setStoreComments(String storeComments) {
        this.storeComments = storeComments;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReportGroupId() {
        return reportGroupId;
    }

    public void setReportGroupId(String reportGroupId) {
        this.reportGroupId = reportGroupId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
