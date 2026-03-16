package com.diana.db_tour_report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class DBProfileDTO {

    private Long id;

    // Basic Information
    private String dbOwnerContact;
    private String coverageArea;
    private String routeStrength;
    private String salesTeam;
    private String vehicleAvailability;
    private String logBookUpdate;

    // Territory Route Map
    private String territoryRouteMap;  // "yes" or "no"
    private String routeMapImage;      // Base64 encoded LONGTEXT

    // Route Plan
    private String routePlan;          // "yes" or "no"
    private String routePlanImage;     // Base64 encoded LONGTEXT

    // Financial Information
    private String creditBillCount;
    private String creditBillTotal;
    private String chequeCount;
    private String chequeTotal;
    private String cashTotal;

    // Progress & SKU Updates
    private String progressSheetUpdate;
    private String skuSalesUpdate;     // "low" or "high"
    private String skuSalesComment;

    // Store Information
    private String storeLength;
    private String storeWidth;
    private String storeCondition;
    private String marketReturnCondition;
    private String tableCount;
    private String chairCount;
    private String storeComments;

    // User & Tracking
    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "Report Group ID is required")
    private String reportGroupId;

    // Timestamps for the History/Dashboard view
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime updatedAt;

    // Default Constructor
    public DBProfileDTO() {
    }

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDbOwnerContact() { return dbOwnerContact; }
    public void setDbOwnerContact(String dbOwnerContact) { this.dbOwnerContact = dbOwnerContact; }

    public String getCoverageArea() { return coverageArea; }
    public void setCoverageArea(String coverageArea) { this.coverageArea = coverageArea; }

    public String getRouteStrength() { return routeStrength; }
    public void setRouteStrength(String routeStrength) { this.routeStrength = routeStrength; }

    public String getSalesTeam() { return salesTeam; }
    public void setSalesTeam(String salesTeam) { this.salesTeam = salesTeam; }

    public String getVehicleAvailability() { return vehicleAvailability; }
    public void setVehicleAvailability(String vehicleAvailability) { this.vehicleAvailability = vehicleAvailability; }

    public String getLogBookUpdate() { return logBookUpdate; }
    public void setLogBookUpdate(String logBookUpdate) { this.logBookUpdate = logBookUpdate; }

    public String getTerritoryRouteMap() { return territoryRouteMap; }
    public void setTerritoryRouteMap(String territoryRouteMap) { this.territoryRouteMap = territoryRouteMap; }

    public String getRouteMapImage() { return routeMapImage; }
    public void setRouteMapImage(String routeMapImage) { this.routeMapImage = routeMapImage; }

    public String getRoutePlan() { return routePlan; }
    public void setRoutePlan(String routePlan) { this.routePlan = routePlan; }

    public String getRoutePlanImage() { return routePlanImage; }
    public void setRoutePlanImage(String routePlanImage) { this.routePlanImage = routePlanImage; }

    public String getCreditBillCount() { return creditBillCount; }
    public void setCreditBillCount(String creditBillCount) { this.creditBillCount = creditBillCount; }

    public String getCreditBillTotal() { return creditBillTotal; }
    public void setCreditBillTotal(String creditBillTotal) { this.creditBillTotal = creditBillTotal; }

    public String getChequeCount() { return chequeCount; }
    public void setChequeCount(String chequeCount) { this.chequeCount = chequeCount; }

    public String getChequeTotal() { return chequeTotal; }
    public void setChequeTotal(String chequeTotal) { this.chequeTotal = chequeTotal; }

    public String getCashTotal() { return cashTotal; }
    public void setCashTotal(String cashTotal) { this.cashTotal = cashTotal; }

    public String getProgressSheetUpdate() { return progressSheetUpdate; }
    public void setProgressSheetUpdate(String progressSheetUpdate) { this.progressSheetUpdate = progressSheetUpdate; }

    public String getSkuSalesUpdate() { return skuSalesUpdate; }
    public void setSkuSalesUpdate(String skuSalesUpdate) { this.skuSalesUpdate = skuSalesUpdate; }

    public String getSkuSalesComment() { return skuSalesComment; }
    public void setSkuSalesComment(String skuSalesComment) { this.skuSalesComment = skuSalesComment; }

    public String getStoreLength() { return storeLength; }
    public void setStoreLength(String storeLength) { this.storeLength = storeLength; }

    public String getStoreWidth() { return storeWidth; }
    public void setStoreWidth(String storeWidth) { this.storeWidth = storeWidth; }

    public String getStoreCondition() { return storeCondition; }
    public void setStoreCondition(String storeCondition) { this.storeCondition = storeCondition; }

    public String getMarketReturnCondition() { return marketReturnCondition; }
    public void setMarketReturnCondition(String marketReturnCondition) { this.marketReturnCondition = marketReturnCondition; }

    public String getTableCount() { return tableCount; }
    public void setTableCount(String tableCount) { this.tableCount = tableCount; }

    public String getChairCount() { return chairCount; }
    public void setChairCount(String chairCount) { this.chairCount = chairCount; }

    public String getStoreComments() { return storeComments; }
    public void setStoreComments(String storeComments) { this.storeComments = storeComments; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}