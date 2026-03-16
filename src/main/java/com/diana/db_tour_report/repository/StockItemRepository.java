package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

    // ✅ REQUIRED FOR PDF & PREVIEW: Fetches all stock rows for one visit
    List<StockItem> findAllByReportGroupId(String reportGroupId);

    // Existing search methods
    List<StockItem> findByUserName(String userName);

    List<StockItem> findByUserRole(String userRole);

    List<StockItem> findByCategoryName(String categoryName);

    List<StockItem> findByUserNameAndCategoryName(String userName, String categoryName);

    List<StockItem> findByItemNameContainingIgnoreCase(String itemName);
}