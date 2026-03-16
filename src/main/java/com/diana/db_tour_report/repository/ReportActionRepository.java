package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.ReportAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReportActionRepository extends JpaRepository<ReportAction, Long> {

    ReportAction findByReportGroupId(String reportGroupId);

    // --- User Mode ---
    List<ReportAction> findByUserNameAndCreatedAtStartingWith(String userName, String datePattern);
    List<ReportAction> findByUserNameAndCreatedAtGreaterThanEqualOrderByCreatedAtDesc(String user, String date);

    // --- Admin Mode (Fuzzy Match for "Western" -> "Western Region") ---

    /**
     * Uses 'Containing' to match partial strings and 'IgnoreCase' for safety.
     * This matches "Western" to "Western Region" or "Western Province".
     */
    List<ReportAction> findByRegionContainingIgnoreCaseAndCreatedAtStartingWith(String region, String datePattern);

    /**
     * Fetches all reports for a region using fuzzy matching, newest first.
     */
    List<ReportAction> findByRegionContainingIgnoreCaseOrderByCreatedAtDesc(String region);

    /**
     * Used for RSM lookup logic.
     */
    List<ReportAction> findByRegionContainingOrderByCreatedAtDesc(String region);


}