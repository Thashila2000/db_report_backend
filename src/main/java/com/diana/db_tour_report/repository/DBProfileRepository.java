package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.DBProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DBProfileRepository extends JpaRepository<DBProfile, Long> {

    /**
     * ✅ Find DB Profile by report group ID (for getting single report)
     */
    Optional<DBProfile> findByReportGroupId(String reportGroupId);

    /**
     * Find all DB Profiles by coverage area
     */
    List<DBProfile> findByCoverageArea(String coverageArea);

    /**
     * Find all DB Profiles by user name
     */
    List<DBProfile> findByUserName(String userName);

    /**
     * Find all DB Profiles that have a territory route map (yes/no)
     */
    List<DBProfile> findByTerritoryRouteMap(String territoryRouteMap);

    /**
     * Find all DB Profiles that have a route plan (yes/no)
     */
    List<DBProfile> findByRoutePlan(String routePlan);

    /**
     * Find all DB Profiles by SKU sales update (low/high)
     */
    List<DBProfile> findBySkuSalesUpdate(String skuSalesUpdate);
}