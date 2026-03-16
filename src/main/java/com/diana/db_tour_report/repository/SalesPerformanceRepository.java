package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.SalesPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface SalesPerformanceRepository extends JpaRepository<SalesPerformance, Long> {

    /**
     * ✅ FETCH BATCH: Get all categories for a specific report
     */
    List<SalesPerformance> findAllByReportGroupId(String reportGroupId);

    /**
     * ✅ CLEAN SYNC: Deletes existing rows before saving a new batch.
     * This prevents duplicate category entries for the same report.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM SalesPerformance s WHERE s.reportGroupId = ?1")
    void deleteByReportGroupId(String reportGroupId);

    List<SalesPerformance> findByUserName(String userName);

    List<SalesPerformance> findByCategory(String category);

    // Useful for management to see performance by role (e.g., all "ASM" reports)
    List<SalesPerformance> findByUserRole(String userRole);
}