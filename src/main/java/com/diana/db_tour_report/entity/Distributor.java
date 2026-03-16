package com.diana.db_tour_report.entity;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "distributor")
public class Distributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "db_name", nullable = false)
    private String dbName;

    @Column(name = "db_code", nullable = false, unique = true)
    private String dbCode;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String area;

    // Store multiple territories in one column (comma-separated string)
    @Column(name = "territories", columnDefinition = "TEXT")
    private String territories;

    // Constructors
    public Distributor() {}

    public Distributor(String dbName, String dbCode, String region, String area, Set<String> territories) {
        this.dbName = dbName;
        this.dbCode = dbCode;
        this.region = region;
        this.area = area;
        setTerritories(territories); // store as comma-separated string
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getDbName() { return dbName; }
    public void setDbName(String dbName) { this.dbName = dbName; }

    public String getDbCode() { return dbCode; }
    public void setDbCode(String dbCode) { this.dbCode = dbCode; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    // Territories handling
    public Set<String> getTerritories() {
        if (territories == null || territories.isEmpty()) return new HashSet<>();
        return new HashSet<>(Arrays.asList(territories.split(",")));
    }

    public void setTerritories(Set<String> territories) {
        this.territories = String.join(",", territories);
    }

    public String getTerritoriesRaw() {
        return territories;
    }

    public void setTerritoriesRaw(String territories) {
        this.territories = territories;
    }
}
