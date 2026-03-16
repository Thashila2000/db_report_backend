package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.ActionStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActionStaffRepository extends JpaRepository<ActionStaff, Long> {

    // ✅ THE MASTER QUERY: Fetches all staff feedback for one specific tour visit
    // Use this to populate the "Staff Feedback" section of your Full Report view
    List<ActionStaff> findAllByReportGroupId(String reportGroupId);

    // ✅ THE SORTING QUERY: Fetches history for a specific user name
    // Supports the "2026.03.09 10.59" sorting requirement
    List<ActionStaff> findByUserNameOrderByCreatedAtDesc(String userName);

    // Find by position (ASM, ASE, or CSR)
    List<ActionStaff> findByPosition(String position);

    // Find by name (staff member name)
    List<ActionStaff> findByNameContainingIgnoreCase(String name);
}

