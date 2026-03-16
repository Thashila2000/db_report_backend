package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.ActionRowDTO;
import com.diana.db_tour_report.dto.ActionsRequest;
import com.diana.db_tour_report.dto.ActionsResponse;
import com.diana.db_tour_report.entity.Action;
import com.diana.db_tour_report.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActionsServiceImpl implements ActionsService {

    private final ActionRepository actionRepository;

    @Autowired
    public ActionsServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public List<ActionsResponse> saveActions(ActionsRequest request) {
        // 🟢 FIX: Extract reportGroupId from request and pass it to convertToEntity
        List<Action> savedActions = request.getRows().stream()
                .map(row -> {
                    Action entity = convertToEntity(
                            row,
                            request.getReportGroupId(),
                            request.getUserName(),
                            request.getUserRole()
                    );
                    return actionRepository.save(entity);
                })
                .collect(Collectors.toList());

        // Convert saved entities to response DTOs
        return savedActions.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ActionsResponse getActionById(Long id) {
        Action entity = actionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Action not found with id: " + id));

        return convertToResponse(entity);
    }

    @Override
    public List<ActionsResponse> getAllActions() {
        List<Action> actions = actionRepository.findAll();

        return actions.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActionsResponse> getActionsByUserName(String userName) {
        List<Action> actions = actionRepository.findByUserName(userName);

        return actions.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActionsResponse> getActionsByUserRole(String userRole) {
        List<Action> actions = actionRepository.findByUserRole(userRole);

        return actions.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActionsResponse> getActionsByFixedStatus(Boolean isFixed) {
        List<Action> actions = actionRepository.findByIsFixed(isFixed);

        return actions.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAction(Long id) {
        if (!actionRepository.existsById(id)) {
            throw new RuntimeException("Action not found with id: " + id);
        }
        actionRepository.deleteById(id);
    }


    private Action convertToEntity(ActionRowDTO row, String reportGroupId, String userName, String userRole) {
        Action entity = new Action();

        entity.setReportGroupId(reportGroupId);

        entity.setAction(row.getAction());
        entity.setComment(row.getComment());
        entity.setIsFixed(row.getIsFixed());
        entity.setUserName(userName);
        entity.setUserRole(userRole);
        return entity;
    }

    private ActionsResponse convertToResponse(Action entity) {
        ActionsResponse response = new ActionsResponse();
        response.setId(entity.getId());
        response.setReportGroupId(entity.getReportGroupId()); // Added this
        response.setAction(entity.getAction());
        response.setComment(entity.getComment());
        response.setIsFixed(entity.getIsFixed());
        response.setUserName(entity.getUserName());
        response.setUserRole(entity.getUserRole());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}