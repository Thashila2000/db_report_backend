package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {

    // THE MASTER QUERY: Fetches all follow-ups for one specific distributor visit
    // Essential for the "Full Table" and PDF generation
    List<FollowUp> findAllByReportGroupId(String reportGroupId);

    // THE SORTING QUERY: Fetches history for a user sorted by the tour time
    // Supports your "Day-by-Day" 15.25 PM sorting requirement
    List<FollowUp> findByUserNameOrderByCreatedAtDesc(String userName);

    // Existing search methods
    List<FollowUp> findByUserName(String userName);

    List<FollowUp> findByUserRole(String userRole);

    // Find by responsible person/department
    List<FollowUp> findByResponsibleContainingIgnoreCase(String responsible);
}