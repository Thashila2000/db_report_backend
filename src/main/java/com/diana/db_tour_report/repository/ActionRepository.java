package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    // ✅ THE MASTER QUERY: Fetches all actions for one specific distributor visit
    // Use this to populate the "Actions Agreed" section in your Full Report view
    List<Action> findAllByReportGroupId(String reportGroupId);

    // ✅ THE SORTING QUERY: Fetches history for a user sorted by the tour time
    // Supports the "2026.03.09 10.59" sorting requirement
    List<Action> findByUserNameOrderByCreatedAtDesc(String userName);

    // Existing search methods
    List<Action> findByUserName(String userName);

    List<Action> findByUserRole(String userRole);

    List<Action> findByIsFixed(Boolean isFixed);

    List<Action> findByActionContainingIgnoreCase(String action);
}