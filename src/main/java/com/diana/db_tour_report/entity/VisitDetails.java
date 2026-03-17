package com.diana.db_tour_report.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "visit_details", indexes = {
        @Index(name = "idx_report_group", columnList = "report_group_id")
})
public class VisitDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region", length = 100)
    private String region;

    @Column(name = "area", length = 100)
    private String area;

    @Column(name = "db_name", length = 200)
    private String dbName;

    @Column(name = "db_code", length = 50)
    private String dbCode;

    @Column(name = "territory_name", length = 100)
    private String territoryName;

    @Column(name = "visited_by", length = 200)
    private String visitedBy;

    @Column(name = "accompanied_by", length = 200)
    private String accompaniedBy;

    /**
     * ✅ New Field for Base64 Image
     * @Lob indicates a Large Object.
     * columnDefinition = "LONGTEXT" is used to ensure MySQL supports
     * large strings (up to 4GB), as standard TEXT is limited to 64KB.
     */
    @Lob
    @Column(name = "accompanied_by_image", columnDefinition = "LONGTEXT")
    private String accompaniedByImage;

    @Column(name = "visit_type", length = 200)
    private String visitType;

    @Column(name = "visit_time")
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime visitTime;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "report_group_id", length = 150, unique = true)
    private String reportGroupId;

    public VisitDetails() {
    }

    @PrePersist
    protected void onCreate() {
        if (this.visitTime == null) {
            this.visitTime = LocalDateTime.now();
        }
    }

    // --- Getters & Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public String getAccompaniedByImage() { return accompaniedByImage; }
    public void setAccompaniedByImage(String accompaniedByImage) { this.accompaniedByImage = accompaniedByImage; }

    public String getVisitType() { return visitType; }
    public void setVisitType(String visitType) { this.visitType = visitType; }

    public LocalDateTime getVisitTime() { return visitTime; }
    public void setVisitTime(LocalDateTime visitTime) { this.visitTime = visitTime; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }
}