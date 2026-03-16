package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.IssuesRequest;
import com.diana.db_tour_report.dto.IssuesResponse;

import java.util.List;

public interface IssuesService {

    // THE MASTER SAVE: Saves a batch of issues for one report
    List<IssuesResponse> saveIssues(IssuesRequest request);

    // THE MASTER FETCH: Gets all issues for one specific tour visit
    // Use this for the "Full Table" view and the PDF generator
    List<IssuesResponse> getIssuesByReportGroupId(String reportGroupId);

    // THE HISTORY FETCH: Gets reports for a user sorted by time
    // Supports the "2026.03.09 10.59" sorting requirement
    List<IssuesResponse> getSortedHistoryByUserName(String userName);

    // Existing methods
    IssuesResponse getIssueById(Long id);
    List<IssuesResponse> getAllIssues();
    List<IssuesResponse> getIssuesByUserName(String userName);
    List<IssuesResponse> getIssuesByUserRole(String userRole);
    List<IssuesResponse> getIssuesBySecurity(String security);
    List<IssuesResponse> getIssuesByFixedStatus(Boolean isFixed);
    void deleteIssue(Long id);
}