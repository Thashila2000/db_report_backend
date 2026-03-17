package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.VisitDetailsDTO;
import com.diana.db_tour_report.entity.VisitDetails;
import com.diana.db_tour_report.repository.VisitDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitDetailsService {

    private final VisitDetailsRepository repository;

    public VisitDetailsService(VisitDetailsRepository repository) {
        this.repository = repository;
    }

    /**
     * Save new visit details
     */
    public VisitDetails saveVisitDetails(VisitDetailsDTO dto) {
        VisitDetails entity = new VisitDetails();

        // Map DTO to Entity
        mapDtoToEntity(dto, entity);

        // Preserve DTO time if provided, else use current server time
        entity.setVisitTime(dto.getVisitTime() != null ? dto.getVisitTime() : LocalDateTime.now());

        return repository.save(entity);
    }

    public Optional<VisitDetails> findByReportGroupId(String reportGroupId) {
        return repository.findByReportGroupId(reportGroupId);
    }

    public List<VisitDetails> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    public List<VisitDetails> findByUserNameAndDateRange(
            String userName,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {
        return repository.findByUserNameAndVisitTimeBetween(userName, startDate, endDate);
    }

    public List<VisitDetails> findByUserNameSorted(String userName) {
        return repository.findByUserNameOrderByVisitTimeDesc(userName);
    }

    public List<VisitDetails> findAll() {
        return repository.findAll();
    }

    public Optional<VisitDetails> findById(Long id) {
        return repository.findById(id);
    }

    public VisitDetails update(Long id, VisitDetailsDTO dto) {
        VisitDetails entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit Details not found with id: " + id));

        // Update fields
        mapDtoToEntity(dto, entity);

        // Optionally update visit time if provided in update DTO
        if (dto.getVisitTime() != null) {
            entity.setVisitTime(dto.getVisitTime());
        }

        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    /**
     * ✅ Helper method to avoid code duplication between save and update
     */
    private void mapDtoToEntity(VisitDetailsDTO dto, VisitDetails entity) {
        entity.setRegion(dto.getRegion());
        entity.setArea(dto.getArea());
        entity.setDbName(dto.getDbName());
        entity.setDbCode(dto.getDbCode());
        entity.setTerritoryName(dto.getTerritoryName());
        entity.setVisitedBy(dto.getVisitedBy());
        entity.setAccompaniedBy(dto.getAccompaniedBy());

        // ✅ Mapping the new Base64 Image field
        entity.setAccompaniedByImage(dto.getAccompaniedByImage());

        entity.setVisitType(dto.getVisitType());
        entity.setUserName(dto.getUserName());
        entity.setReportGroupId(dto.getReportGroupId());
    }
}