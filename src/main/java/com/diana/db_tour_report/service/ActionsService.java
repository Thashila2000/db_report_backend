package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.ActionsRequest;
import com.diana.db_tour_report.dto.ActionsResponse;

import java.util.List;

public interface ActionsService {

    // Save actions (all rows at once)
    List<ActionsResponse> saveActions(ActionsRequest request);

    // Get action by ID
    ActionsResponse getActionById(Long id);

    // Get all actions
    List<ActionsResponse> getAllActions();

    // Get actions by user name
    List<ActionsResponse> getActionsByUserName(String userName);

    // Get actions by user role
    List<ActionsResponse> getActionsByUserRole(String userRole);

    // Get actions by isFixed status
    List<ActionsResponse> getActionsByFixedStatus(Boolean isFixed);

    // Delete action
    void deleteAction(Long id);
}
