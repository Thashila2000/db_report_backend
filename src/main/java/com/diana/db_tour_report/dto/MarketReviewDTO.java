package com.diana.db_tour_report.dto;

public class MarketReviewDTO {

    private String reportGroupId;
    private String region;        // Added
    private String territoryName; // Added
    private String routeName;     // Added
    private String area;
    private String observation;
    private String imageBase64;
    private Boolean isDefault;
    private String userName;
    private String userRole;

    public MarketReviewDTO() {}

    // --- Added Getters and Setters ---

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getTerritoryName() { return territoryName; }
    public void setTerritoryName(String territoryName) { this.territoryName = territoryName; }

    public String getRouteName() { return routeName; }
    public void setRouteName(String routeName) { this.routeName = routeName; }

    // --- Existing Getters and Setters ---

    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    public String getImageBase64() { return imageBase64; }
    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }

    public Boolean getIsDefault() { return isDefault; }
    public void setIsDefault(Boolean isDefault) { this.isDefault = isDefault; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
}