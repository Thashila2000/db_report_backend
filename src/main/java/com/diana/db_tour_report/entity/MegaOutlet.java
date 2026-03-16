package com.diana.db_tour_report.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mega_outlets")
public class MegaOutlet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_group_id", length = 100)
    private String reportGroupId;

    // --- Global Location Context Fields ---
    private String region;
    private String territoryName;
    private String routeName;

    private String name;
    private String sales;
    private String discount;
    private String sku;

    @Column(name = "outlet_image_base64", columnDefinition = "LONGTEXT")
    private String outletImageBase64;

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

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSales() { return sales; }
    public void setSales(String sales) { this.sales = sales; }

    public String getDiscount() { return discount; }
    public void setDiscount(String discount) { this.discount = discount; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getOutletImageBase64() { return outletImageBase64; }
    public void setOutletImageBase64(String outletImageBase64) { this.outletImageBase64 = outletImageBase64; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}