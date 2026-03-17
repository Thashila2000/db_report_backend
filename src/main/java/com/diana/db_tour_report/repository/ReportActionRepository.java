package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.ReportAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReportActionRepository extends JpaRepository<ReportAction, Long> {

    /**
     * Finds the summary record for a specific report group.
     */
    ReportAction findByReportGroupId(String reportGroupId);

    // --- User Mode ---

    /**
     * Filters by username and a specific date pattern (e.g., "2026.03").
     */
    List<ReportAction> findByUserNameAndCreatedAtStartingWith(String userName, String datePattern);

    /**
     * Fetches recent reports for a user within the last 30 days (standard dashboard view).
     */
    List<ReportAction> findByUserNameAndCreatedAtGreaterThanEqualOrderByCreatedAtDesc(String user, String date);

    // --- Admin Mode (Fuzzy Match) ---

    /**
     * Partial match for Region (e.g., "West" matches "Western Region") and Date.
     */
    List<ReportAction> findByRegionContainingIgnoreCaseAndCreatedAtStartingWith(String region, String datePattern);

    /**
     * Fetches all reports for a region using fuzzy matching, newest first.
     */
    List<ReportAction> findByRegionContainingIgnoreCaseOrderByCreatedAtDesc(String region);

    /**
     * Used for RSM lookup logic in ReportService.
     * Uses 'Containing' to ensure we find the most recent activity even with partial names.
     */
    List<ReportAction> findByRegionContainingOrderByCreatedAtDesc(String region);
}