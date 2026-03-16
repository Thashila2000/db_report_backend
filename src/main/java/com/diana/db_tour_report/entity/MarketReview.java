package com.diana.db_tour_report.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "market_reviews")
public class MarketReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_group_id", length = 100)
    private String reportGroupId;

    // New Fields for Mapping
    private String region;
    private String territoryName;
    private String routeName;

    @Column(columnDefinition = "TEXT")
    private String area; // Changed from 'areas' to represent a single row

    @Column(columnDefinition = "TEXT")
    private String observation; // Changed from 'observations'

    @Column(name = "image_base64", columnDefinition = "LONGTEXT")
    private String imageBase64;

    private String userName;
    private String userRole;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getTerritoryName() { return territoryName; }
    public void setTerritoryName(String territoryName) { this.territoryName = territoryName; }

    public String getRouteName() { return routeName; }
    public void setRouteName(String routeName) { this.routeName = routeName; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    public String getImageBase64() { return imageBase64; }
    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}