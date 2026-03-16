package com.diana.db_tour_report.dto;

import java.util.List;

/**
 * Wrapper DTO for the Market Execution submission.
 * This matches the "masterPayload" sent from the React frontend.
 */
public class MarketReviewRequest {

    private String reportGroupId;
    private String userName;
    private String userRole;

    // Global Context Fields
    private String region;
    private String territoryName;
    private String routeName;

    // Nested Lists for the two tables
    private List<ReviewRowDTO> reviews;
    private List<OutletDTO> outlets; // Added to capture Mega Outlet data

    public MarketReviewRequest() {
    }

    // --- Getters and Setters ---

    public String getReportGroupId() {
        return reportGroupId;
    }

    public void setReportGroupId(String reportGroupId) {
        this.reportGroupId = reportGroupId;
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

    public List<ReviewRowDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewRowDTO> reviews) {
        this.reviews = reviews;
    }

    public List<OutletDTO> getOutlets() {
        return outlets;
    }

    public void setOutlets(List<OutletDTO> outlets) {
        this.outlets = outlets;
    }
}