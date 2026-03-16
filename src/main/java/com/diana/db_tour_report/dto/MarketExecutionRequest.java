package com.diana.db_tour_report.dto;

import java.util.List;

public class MarketExecutionRequest {
    private String reportGroupId;
    private String userName;
    private String userRole;

    // --- ADDED THESE FIELDS ---
    private String region;
    private String territoryName;
    private String routeName;
    // --------------------------

    private List<ReviewRowDTO> reviews;
    private List<OutletDTO> outlets;

    public MarketExecutionRequest() {
    }

    // --- ADDED GETTERS AND SETTERS FOR NEW FIELDS ---

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

    // --- EXISTING GETTERS AND SETTERS ---

    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
    public List<ReviewRowDTO> getReviews() { return reviews; }
    public void setReviews(List<ReviewRowDTO> reviews) { this.reviews = reviews; }
    public List<OutletDTO> getOutlets() { return outlets; }
    public void setOutlets(List<OutletDTO> outlets) { this.outlets = outlets; }
}