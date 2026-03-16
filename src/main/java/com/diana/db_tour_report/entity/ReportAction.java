package com.diana.db_tour_report.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "reports")
public class ReportAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_group_id")
    private String reportGroupId;

    @Column(name = "user_name")
    private String userName;

    @JsonProperty("territoryName")
    @Column(name = "territory_name")
    private String territoryName;

    private String dbName;
    private String dbCode;
    private String region;
    private String area;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "visit_time")
    private String visitTime;

    public ReportAction() {}


    @JsonProperty("visit_details")
    public Map<String, String> getVisitDetails() {
        Map<String, String> details = new HashMap<>();
        details.put("visit_time", this.visitTime != null ? this.visitTime : "N/A");
        return details;
    }

    // Standard Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getTerritoryName() { return territoryName; }
    public void setTerritoryName(String territoryName) { this.territoryName = territoryName; }

    public String getDbName() { return dbName; }
    public void setDbName(String dbName) { this.dbName = dbName; }

    public String getDbCode() { return dbCode; }
    public void setDbCode(String dbCode) { this.dbCode = dbCode; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getVisitTime() { return visitTime; }
    public void setVisitTime(String visitTime) { this.visitTime = visitTime; }
}