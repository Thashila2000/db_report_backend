package com.diana.db_tour_report.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

public class VisitDetailsDTO {

    private String region;
    private String area;
    private String dbName;
    private String dbCode;
    private String territoryName;
    private String visitedBy;
    private String accompaniedBy;
    private String visitType;

    // Ensures the JSON from React/Vite matches your reporting format
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime visitTime;

    private String userName;


    @NotBlank(message = "Report Group ID is required")
    private String reportGroupId;

    public VisitDetailsDTO() {
    }

    // --- Getters and Setters ---

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getDbName() { return dbName; }
    public void setDbName(String dbName) { this.dbName = dbName; }

    public String getDbCode() { return dbCode; }
    public void setDbCode(String dbCode) { this.dbCode = dbCode; }

    public String getTerritoryName() { return territoryName; }
    public void setTerritoryName(String territoryName) { this.territoryName = territoryName; }

    public String getVisitedBy() { return visitedBy; }
    public void setVisitedBy(String visitedBy) { this.visitedBy = visitedBy; }

    public String getAccompaniedBy() { return accompaniedBy; }
    public void setAccompaniedBy(String accompaniedBy) { this.accompaniedBy = accompaniedBy; }

    public String getVisitType() { return visitType; }
    public void setVisitType(String visitType) { this.visitType = visitType; }

    public LocalDateTime getVisitTime() { return visitTime; }
    public void setVisitTime(LocalDateTime visitTime) { this.visitTime = visitTime; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }
}