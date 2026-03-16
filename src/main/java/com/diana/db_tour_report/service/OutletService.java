package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.OutletDTO;
import com.diana.db_tour_report.entity.MegaOutlet;
import com.diana.db_tour_report.repository.OutletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OutletService {

    private final OutletRepository repository;

    public OutletService(OutletRepository repository) {
        this.repository = repository;
    }

    /**
     * Fix for: Cannot resolve method 'save'
     */
    public MegaOutlet save(OutletDTO dto) {
        MegaOutlet entity = new MegaOutlet();
        entity.setReportGroupId(dto.getReportGroupId());
        entity.setRegion(dto.getRegion());
        entity.setTerritoryName(dto.getTerritoryName());
        entity.setRouteName(dto.getRouteName());

        entity.setName(dto.getName());
        entity.setSales(dto.getSales());
        entity.setDiscount(dto.getDiscount());
        entity.setSku(dto.getSku());
        entity.setOutletImageBase64(dto.getOutletImageBase64());

        entity.setUserName(dto.getUserName());
        entity.setUserRole(dto.getUserRole());

        return repository.save(entity);
    }

    /**
     * Saves a list of outlets with context injection
     */
    public List<MegaOutlet> saveAll(List<OutletDTO> dtos, String groupId, String region, String territory, String route, String user, String role) {
        List<MegaOutlet> entities = dtos.stream().map(dto -> {
            MegaOutlet entity = new MegaOutlet();
            entity.setReportGroupId(groupId);
            entity.setRegion(region);
            entity.setTerritoryName(territory);
            entity.setRouteName(route);
            entity.setUserName(user);
            entity.setUserRole(role);
            entity.setName(dto.getName());
            entity.setSales(dto.getSales());
            entity.setDiscount(dto.getDiscount());
            entity.setSku(dto.getSku());
            entity.setOutletImageBase64(dto.getOutletImageBase64());
            return entity;
        }).collect(Collectors.toList());

        return repository.saveAll(entities);
    }
}