package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DistributorRepository extends JpaRepository<Distributor, Long> {

    // Find distributors by region
    List<Distributor> findByRegion(String region);

    // Find distributors by region and area
    List<Distributor> findByRegionAndArea(String region, String area);

    // Find distributors where territories column contains a keyword
    @Query("SELECT d FROM Distributor d WHERE d.territories LIKE %:territory%")
    List<Distributor> findByTerritory(String territory);
}
