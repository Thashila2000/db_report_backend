package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.IssueRowDTO;
import com.diana.db_tour_report.dto.IssuesRequest;
import com.diana.db_tour_report.dto.IssuesResponse;
import com.diana.db_tour_report.entity.Issue;
import com.diana.db_tour_report.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class IssuesServiceImpl implements IssuesService {

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public List<IssuesResponse> saveIssues(IssuesRequest request) {
        // Map the reportGroupId from the request to every individual row
        List<Issue> savedIssues = request.getRows().stream()
                .map(row -> {
                    Issue entity = convertToEntity(row, request.getUserName(),
                            request.getUserRole(), request.getReportGroupId());
                    return issueRepository.save(entity);
                })
                .collect(Collectors.toList());

        return savedIssues.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<IssuesResponse> getIssuesByReportGroupId(String reportGroupId) {
        // ✅ Fetches all issues for a single visit (Perfect for the "Full Table" view)
        return issueRepository.findAllByReportGroupId(reportGroupId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<IssuesResponse> getSortedHistoryByUserName(String userName) {
        // ✅ Fetches history for the dashboard sorted by high-precision time
        return issueRepository.findByUserNameOrderByCreatedAtDesc(userName).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public IssuesResponse getIssueById(Long id) {
        Issue entity = issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found with id: " + id));
        return convertToResponse(entity);
    }

    @Override
    public List<IssuesResponse> getAllIssues() {
        return issueRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // --- Search Methods ---

    @Override
    public List<IssuesResponse> getIssuesByUserName(String userName) {
        return issueRepository.findByUserName(userName).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<IssuesResponse> getIssuesByUserRole(String userRole) {
        return issueRepository.findByUserRole(userRole).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<IssuesResponse> getIssuesBySecurity(String security) {
        return issueRepository.findBySecurity(security).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<IssuesResponse> getIssuesByFixedStatus(Boolean isFixed) {
        return issueRepository.findByIsFixed(isFixed).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteIssue(Long id) {
        if (!issueRepository.existsById(id)) {
            throw new RuntimeException("Issue not found with id: " + id);
        }
        issueRepository.deleteById(id);
    }

    // --- Helper Mappings ---

    private Issue convertToEntity(IssueRowDTO row, String userName, String userRole, String reportGroupId) {
        Issue entity = new Issue();
        entity.setReportGroupId(reportGroupId); // Critical link for the Full Table view
        entity.setIssueType(row.getIssueType());
        entity.setDescription(row.getDescription());
        entity.setSecurity(row.getSecurity());
        entity.setIsFixed(row.getIsFixed());
        entity.setUserName(userName);
        entity.setUserRole(userRole);
        return entity;
    }

    private IssuesResponse convertToResponse(Issue entity) {
        IssuesResponse response = new IssuesResponse();
        response.setId(entity.getId());
        response.setReportGroupId(entity.getReportGroupId());
        response.setIssueType(entity.getIssueType());
        response.setDescription(entity.getDescription());
        response.setSecurity(entity.getSecurity());
        response.setIsFixed(entity.getIsFixed());
        response.setUserName(entity.getUserName());
        response.setUserRole(entity.getUserRole());

        // Formatted as 2026.03.09 10.59 via @JsonFormat in Entity/Response
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}