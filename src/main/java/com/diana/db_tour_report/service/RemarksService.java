package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.RemarksDTO;
import com.diana.db_tour_report.dto.RemarksResponse;
import com.diana.db_tour_report.entity.Remarks;
import com.diana.db_tour_report.repository.RemarksRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RemarksService {

    private final RemarksRepository repository;

    public RemarksService(RemarksRepository repository) {
        this.repository = repository;
    }

    // Uses the reportGroupId to link these remarks to the visit
    public RemarksResponse saveRemarks(RemarksDTO dto) {
        Remarks entity = new Remarks();

        // Essential Link for the "Full Table" view
        entity.setReportGroupId(dto.getReportGroupId());

        entity.setRemarks(dto.getRemarks());
        entity.setPreparedBy(dto.getPreparedBy());
        entity.setUserName(dto.getUserName());
        entity.setUserRole(dto.getUserRole());

        Remarks saved = repository.save(entity);
        return convertToResponse(saved);
    }

    // FETCH BY VISIT: For the consolidated report view
    public List<RemarksResponse> getRemarksByReportGroupId(String reportGroupId) {
        return repository.findAllByReportGroupId(reportGroupId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // FETCH HISTORY: For the user's dashboard (Day-by-Day sorting)
    public List<RemarksResponse> getSortedHistory(String userName) {
        return repository.findByUserNameOrderByCreatedAtDesc(userName).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // HELPER: Maps Entity to Response DTO
    private RemarksResponse convertToResponse(Remarks entity) {
        RemarksResponse res = new RemarksResponse();
        res.setId(entity.getId());
        res.setReportGroupId(entity.getReportGroupId());
        res.setRemarks(entity.getRemarks());
        res.setPreparedBy(entity.getPreparedBy());
        res.setUserName(entity.getUserName());
        res.setUserRole(entity.getUserRole());
        res.setCreatedAt(entity.getCreatedAt()); // Formats as 2026.03.09 15.42
        return res;
    }

    // FETCH SINGLE: Specifically for the Modal Preview
    public RemarksResponse getSingleRemarksByGroupId(String reportGroupId) {
        Remarks entity = repository.findByReportGroupId(reportGroupId);
        return entity != null ? convertToResponse(entity) : null;
    }




}