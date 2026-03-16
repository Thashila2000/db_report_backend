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
     * Find the specific visit header to link with all 8 tables
     */
    Optional<VisitDetails> findByReportGroupId(String reportGroupId);

    /**
     * Get all visits for a user, sorted by time (Newest First)
     */
    List<VisitDetails> findByUserNameOrderByVisitTimeDesc(String userName);

    /**
     * Used for generating monthly or weekly summary reports
     */
    List<VisitDetails> findByUserNameAndVisitTimeBetweenOrderByVisitTimeDesc(
            String userName,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    // --- Search & Filter Methods ---
    List<VisitDetails> findByRegion(String region);
    List<VisitDetails> findByArea(String area);
    List<VisitDetails> findByDbNameContainingIgnoreCase(String dbName);

    // Check if a report already exists to prevent duplicate submissions
    boolean existsByReportGroupId(String reportGroupId);

    List<VisitDetails> findByUserNameAndVisitTimeBetween(String userName, LocalDateTime startDate, LocalDateTime endDate);

    List<VisitDetails> findByUserName(String userName);

    List<VisitDetails> findAllByReportGroupId(String reportGroupId);
}