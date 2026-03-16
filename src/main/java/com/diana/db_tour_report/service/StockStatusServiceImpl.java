package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.StockCategoryDTO;
import com.diana.db_tour_report.dto.StockItemDTO;
import com.diana.db_tour_report.dto.StockStatusRequest;
import com.diana.db_tour_report.dto.StockStatusResponse;
import com.diana.db_tour_report.entity.StockItem;
import com.diana.db_tour_report.repository.StockItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StockStatusServiceImpl implements StockStatusService {

    @Autowired
    private StockItemRepository stockItemRepository;

    @Override
    public List<StockStatusResponse> saveStockStatus(StockStatusRequest request) {
        List<StockItem> allItems = new ArrayList<>();

        // 🟢 FIX: Extract the Master Report ID once from the request
        String reportGroupId = request.getReportGroupId();

        // Process each category
        for (StockCategoryDTO category : request.getCategories()) {
            // Process each item in the category
            for (StockItemDTO itemDTO : category.getItems()) {
                // 🟢 FIX: Passing reportGroupId to the entity creator
                StockItem entity = convertToEntity(
                        itemDTO,
                        category.getName(),
                        category.getComment(),
                        request.getUserName(),
                        request.getUserRole(),
                        reportGroupId
                );
                allItems.add(entity);
            }
        }

        // Save all items (every row now has a reportGroupId)
        List<StockItem> savedItems = stockItemRepository.saveAll(allItems);

        return savedItems.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StockStatusResponse getStockItemById(Long id) {
        StockItem entity = stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock item not found with id: " + id));
        return convertToResponse(entity);
    }

    @Override
    public List<StockStatusResponse> getAllStockItems() {
        return stockItemRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockStatusResponse> getStockItemsByUserName(String userName) {
        return stockItemRepository.findByUserName(userName).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockStatusResponse> getStockItemsByUserRole(String userRole) {
        return stockItemRepository.findByUserRole(userRole).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockStatusResponse> getStockItemsByCategoryName(String categoryName) {
        return stockItemRepository.findByCategoryName(categoryName).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStockItem(Long id) {
        if (!stockItemRepository.existsById(id)) {
            throw new RuntimeException("Stock item not found with id: " + id);
        }
        stockItemRepository.deleteById(id);
    }

    private String calculateVariance(String stockLevel, String systemStock) {
        try {
            double stock = Double.parseDouble(stockLevel);
            double system = Double.parseDouble(systemStock);
            double variance = stock - system;
            return String.format("%s%.2f", variance >= 0 ? "+" : "", variance);
        } catch (Exception e) {
            return "0.00";
        }
    }

    // 🟢 UPDATED HELPER: Now sets the reportGroupId
    private StockItem convertToEntity(
            StockItemDTO itemDTO,
            String categoryName,
            String categoryComment,
            String userName,
            String userRole,
            String reportGroupId) { // 👈 New parameter

        StockItem entity = new StockItem();

        // 🔥 CRITICAL: Every row must include report id
        entity.setReportGroupId(reportGroupId);

        entity.setCategoryName(categoryName);
        entity.setItemName(itemDTO.getItemName());
        entity.setStockLevel(itemDTO.getStockLevel());
        entity.setSystemStock(itemDTO.getSystemStock());
        entity.setVariance(calculateVariance(itemDTO.getStockLevel(), itemDTO.getSystemStock()));
        entity.setCategoryComment(categoryComment);
        entity.setUserName(userName);
        entity.setUserRole(userRole);

        return entity;
    }

    private StockStatusResponse convertToResponse(StockItem entity) {
        StockStatusResponse response = new StockStatusResponse();
        response.setId(entity.getId());
        // 🟢 FIX: Include the reportGroupId in the response
        response.setReportGroupId(entity.getReportGroupId());
        response.setCategoryName(entity.getCategoryName());
        response.setItemName(entity.getItemName());
        response.setStockLevel(entity.getStockLevel());
        response.setSystemStock(entity.getSystemStock());
        response.setVariance(entity.getVariance());
        response.setCategoryComment(entity.getCategoryComment());
        response.setUserName(entity.getUserName());
        response.setUserRole(entity.getUserRole());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}