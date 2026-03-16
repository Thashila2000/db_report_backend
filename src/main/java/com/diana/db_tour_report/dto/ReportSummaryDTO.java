package com.diana.db_tour_report.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportSummaryDTO {
    private String reportGroupId;
    private String visitTime;
    private String dbName;
    private String dbCode;
    private String region;
    private String area;
    @JsonProperty("territoryName")
    private String territoryName;
    private String userName;

    public ReportSummaryDTO() {}

    public ReportSummaryDTO(String reportGroupId, String visitTime, String dbName,
                            String dbCode, String region, String area, String territoryName, String userName) {
        this.reportGroupId = reportGroupId;
        this.visitTime = visitTime;
        this.dbName = dbName;
        this.dbCode = dbCode;
        this.region = region;
        this.area = area;
        this.territoryName = territoryName;
        this.userName = userName;
    }

    // --- Getters ---
    public String getReportGroupId() { return reportGroupId; }
    public String getVisitTime() { return visitTime; }
    public String getDbName() { return dbName; }
    public String getDbCode() { return dbCode; }
    public String getRegion() { return region; }
    public String getArea() { return area; }
    public String getTerritoryName() { return territoryName; }
    public String getUserName() { return userName; }

    // --- Setters ---
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }
    public void setVisitTime(String visitTime) { this.visitTime = visitTime; }
    public void setDbName(String dbName) { this.dbName = dbName; }
    public void setDbCode(String dbCode) { this.dbCode = dbCode; }
    public void setRegion(String region) { this.region = region; }
    public void setArea(String area) { this.area = area; }
    public void setTerritoryName(String territoryName) { this.territoryName = territoryName; }
    public void setUserName(String userName) { this.userName = userName; }
}