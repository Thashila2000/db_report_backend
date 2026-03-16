package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.StockStatusRequest;
import com.diana.db_tour_report.dto.StockStatusResponse;
import java.util.List;

public interface StockStatusService {

    // Save stock status data (all categories and items at once)
    List<StockStatusResponse> saveStockStatus(StockStatusRequest request);

    // Get stock item by ID
    StockStatusResponse getStockItemById(Long id);

    // Get all stock items
    List<StockStatusResponse> getAllStockItems();

    // Get stock items by user name
    List<StockStatusResponse> getStockItemsByUserName(String userName);

    // Get stock items by user role
    List<StockStatusResponse> getStockItemsByUserRole(String userRole);

    // Get stock items by category name
    List<StockStatusResponse> getStockItemsByCategoryName(String categoryName);

    // Delete stock item
    void deleteStockItem(Long id);
}
