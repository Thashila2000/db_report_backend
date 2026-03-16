package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.MegaOutlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OutletRepository extends JpaRepository<MegaOutlet, Long> {

    /**
     * Finds all outlets associated with a specific report submission.
     */
    List<MegaOutlet> findByReportGroupId(String reportGroupId);

    /**
     * Finds outlets by the username of the person who submitted.
     */
    List<MegaOutlet> findByUserName(String userName);

    // --- Admin/Location-Based Queries ---

    /**
     * CHANGED: Use 'Containing' to match partial strings.
     * This matches "Western" to "Western Region".
     */
    List<MegaOutlet> findByRegionContainingIgnoreCase(String region);

    /**
     * CHANGED: Use 'Containing' for flexible Territory matching.
     */
    List<MegaOutlet> findByTerritoryNameContainingIgnoreCase(String territoryName);

    /**
     * CHANGED: Use 'Containing' for flexible Route matching.
     */
    List<MegaOutlet> findByRouteNameContainingIgnoreCase(String routeName);

    /**
     * CHANGED: Find outlets based on partial Region and Territory.
     */
    List<MegaOutlet> findByRegionContainingIgnoreCaseAndTerritoryNameContainingIgnoreCase(String region, String territoryName);
}