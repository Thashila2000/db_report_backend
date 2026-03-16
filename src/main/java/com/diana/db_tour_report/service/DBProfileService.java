package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.DBProfileDTO;
import com.diana.db_tour_report.entity.DBProfile;
import com.diana.db_tour_report.repository.DBProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional // ✅ Ensures data integrity during heavy image uploads
public class DBProfileService {

    private final DBProfileRepository repository;

    // ✅ Constructor Injection (Best Practice)
    public DBProfileService(DBProfileRepository repository) {
        this.repository = repository;
    }

    /**
     * Save a new DB Profile
     */
    public DBProfile save(DBProfileDTO dto) {
        DBProfile entity = new DBProfile();
        mapDtoToEntity(dto, entity);
        return repository.save(entity);
    }

    /**
     * Update existing DB Profile
     */
    public DBProfile update(Long id, DBProfileDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    mapDtoToEntity(dto, entity);
                    return repository.save(entity);
                })
                .orElseThrow(() -> new RuntimeException("DB Profile not found with id: " + id));
    }

    /**
     * ✅ Helper Method to avoid repeating code in Save and Update
     */
    private void mapDtoToEntity(DBProfileDTO dto, DBProfile entity) {
        // Basic Information
        entity.setDbOwnerContact(dto.getDbOwnerContact());
        entity.setCoverageArea(dto.getCoverageArea());
        entity.setRouteStrength(dto.getRouteStrength());
        entity.setSalesTeam(dto.getSalesTeam());
        entity.setVehicleAvailability(dto.getVehicleAvailability());
        entity.setLogBookUpdate(dto.getLogBookUpdate());

        // Territory Route Map & Images
        entity.setTerritoryRouteMap(dto.getTerritoryRouteMap());
        entity.setRouteMapImage(dto.getRouteMapImage());
        entity.setRoutePlan(dto.getRoutePlan());
        entity.setRoutePlanImage(dto.getRoutePlanImage());

        // Financial Information
        entity.setCreditBillCount(dto.getCreditBillCount());
        entity.setCreditBillTotal(dto.getCreditBillTotal());
        entity.setChequeCount(dto.getChequeCount());
        entity.setChequeTotal(dto.getChequeTotal());
        entity.setCashTotal(dto.getCashTotal());

        // Progress & SKU Updates
        entity.setProgressSheetUpdate(dto.getProgressSheetUpdate());
        entity.setSkuSalesUpdate(dto.getSkuSalesUpdate());
        entity.setSkuSalesComment(dto.getSkuSalesComment());

        // Store Information
        entity.setStoreLength(dto.getStoreLength());
        entity.setStoreWidth(dto.getStoreWidth());
        entity.setStoreCondition(dto.getStoreCondition());
        entity.setMarketReturnCondition(dto.getMarketReturnCondition());
        entity.setTableCount(dto.getTableCount());
        entity.setChairCount(dto.getChairCount());
        entity.setStoreComments(dto.getStoreComments());

        // Tracking & Master Link
        entity.setUserName(dto.getUserName());
        entity.setReportGroupId(dto.getReportGroupId());
    }

    // --- Search Methods ---

    public List<DBProfile> findAll() { return repository.findAll(); }

    public Optional<DBProfile> findById(Long id) { return repository.findById(id); }

    public Optional<DBProfile> findByReportGroupId(String reportGroupId) {
        return repository.findByReportGroupId(reportGroupId);
    }

    public List<DBProfile> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    public void deleteById(Long id) { repository.deleteById(id); }
}