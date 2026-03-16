package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    // ✅ THE MASTER QUERY: Fetches all issues for a specific visit/tour
    // Use this to display the "Full Table" or generate the PDF
    List<Issue> findAllByReportGroupId(String reportGroupId);

    // ✅ THE SORTING QUERY: Fetches issues for a user sorted by date/time
    // This supports your "2026.03.09 10.59" sorting requirement
    List<Issue> findByUserNameOrderByCreatedAtDesc(String userName);

    // Existing search methods
    List<Issue> findByUserName(String userName);
    List<Issue> findByUserRole(String userRole);
    List<Issue> findBySecurity(String security);
    List<Issue> findByIsFixed(Boolean isFixed);
    List<Issue> findByIssueTypeContainingIgnoreCase(String issueType);
    List<Issue> findByUserNameAndSecurity(String userName, String security);
}