package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.FollowUpRequest;
import com.diana.db_tour_report.dto.FollowUpResponse;
import com.diana.db_tour_report.entity.FollowUp;
import com.diana.db_tour_report.repository.FollowUpRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FollowUpService {

    private final FollowUpRepository repository;

    public FollowUpService(FollowUpRepository repository) {
        this.repository = repository;
    }

    // ✅ Change return type to List<FollowUpResponse>
    public List<FollowUpResponse> saveFollowUps(FollowUpRequest request) {
        if (request.getRows() == null) return List.of();

        List<FollowUp> entities = request.getRows().stream()
                .map(rowDto -> {
                    FollowUp entity = new FollowUp();
                    entity.setReportGroupId(request.getReportGroupId());
                    entity.setAction(rowDto.getAction());
                    entity.setResponsible(rowDto.getResponsible());
                    entity.setDeadline(rowDto.getDeadline());
                    entity.setUserName(request.getUserName());
                    entity.setUserRole(request.getUserRole());
                    return entity;
                })
                .collect(Collectors.toList());

        // Save the entities
        List<FollowUp> savedEntities = repository.saveAll(entities);

        // Convert saved Entities back to Response DTOs
        return savedEntities.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    private FollowUpResponse convertToResponse(FollowUp entity) {
        FollowUpResponse response = new FollowUpResponse();
        response.setId(entity.getId());
        response.setReportGroupId(entity.getReportGroupId());
        response.setAction(entity.getAction());
        response.setResponsible(entity.getResponsible());
        response.setDeadline(entity.getDeadline());
        response.setUserName(entity.getUserName());
        response.setUserRole(entity.getUserRole());
        response.setCreatedAt(entity.getCreatedAt()); // Formats as 2026.03.09 15.33
        return response;
    }
    // ✅ Add this method to resolve the "cannot find symbol" error in the Controller
    public List<FollowUpResponse> getFollowUpsByReportGroupId(String reportGroupId) {
        return repository.findAllByReportGroupId(reportGroupId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // ✅ Add this one too, as your Controller likely needs it for the history view
    public List<FollowUpResponse> getSortedHistory(String userName) {
        return repository.findByUserNameOrderByCreatedAtDesc(userName).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
}