package com.diana.db_tour_report.dto;

public class OutletDTO {

    private String reportGroupId; // Added to link to the master report
    private String region;        // Added
    private String territoryName; // Added
    private String routeName;     // Added

    private String name;
    private String sales;
    private String discount;
    private String sku;
    private String outletImageBase64;

    private String userName;
    private String userRole;

    public OutletDTO() {
    }

    // --- Added Getters and Setters ---

    public String getReportGroupId() {
        return reportGroupId;
    }

    public void setReportGroupId(String reportGroupId) {
        this.reportGroupId = reportGroupId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTerritoryName() {
        return territoryName;
    }

    public void setTerritoryName(String territoryName) {
        this.territoryName = territoryName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    // --- Existing Getters and Setters ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getOutletImageBase64() {
        return outletImageBase64;
    }

    public void setOutletImageBase64(String outletImageBase64) {
        this.outletImageBase64 = outletImageBase64;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}