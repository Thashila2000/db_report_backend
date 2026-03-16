package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.MarketReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MarketReviewRepository extends JpaRepository<MarketReview, Long> {

    // Find all rows for a specific visit session
    List<MarketReview> findByReportGroupId(String reportGroupId);

    List<MarketReview> findByUserName(String userName);

    List<MarketReview> findByUserRole(String userRole);

    // --- New Query Methods for Location-Based Reporting ---

    /**
     * Get all performance records for a specific Region (e.g., "Western")
     */
    List<MarketReview> findByRegion(String region);

    /**
     * Get all records for a specific Territory (e.g., "Colombo South")
     */
    List<MarketReview> findByTerritoryName(String territoryName);

    /**
     * Get all records for a specific Route
     */
    List<MarketReview> findByRouteName(String routeName);

    /**
     * Advanced: Find all reviews for a specific territory within a specific region
     */
    List<MarketReview> findByRegionAndTerritoryName(String region, String territoryName);
}