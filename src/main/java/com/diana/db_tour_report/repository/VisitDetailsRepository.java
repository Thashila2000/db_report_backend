package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.VisitDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitDetailsRepository extends JpaRepository<VisitDetails, Long> {

    /**
     * Find the specific visit header to link with other report tables.
     * reportGroupId is unique, so we return an Optional.
     */
    Optional<VisitDetails> findByReportGroupId(String reportGroupId);

    /**
     * Get all visits for a specific user.
     */
    List<VisitDetails> findByUserName(String userName);

    /**
     * Get all visits for a user, sorted by time (Newest First).
     */
    List<VisitDetails> findByUserNameOrderByVisitTimeDesc(String userName);

    /**
     * Used for generating monthly or weekly summary reports with sorting.
     */
    List<VisitDetails> findByUserNameAndVisitTimeBetweenOrderByVisitTimeDesc(
            String userName,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    /**
     * Standard date range filter (used by the Service's findByUserNameAndDateRange).
     */
    List<VisitDetails> findByUserNameAndVisitTimeBetween(
            String userName,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    // --- Search & Filter Methods ---
    List<VisitDetails> findByRegion(String region);
    List<VisitDetails> findByArea(String area);
    List<VisitDetails> findByDbNameContainingIgnoreCase(String dbName);

    /**
     * Prevents duplicate report submissions by checking the unique Group ID.
     */
    boolean existsByReportGroupId(String reportGroupId);
}