package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.entity.Distributor;
import com.diana.db_tour_report.repository.DistributorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/distributors")
public class DistributorController {

    private final DistributorRepository distributorRepository;

    public DistributorController(DistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

    // ✅ NEW: Get distributors by multiple regions (comma-separated)
    @GetMapping("/by-regions")
    public List<Map<String, String>> getDistributorsByRegions(@RequestParam String regions) {
        // Split comma-separated regions: "Western, Central, Southern"
        List<String> regionList = Arrays.stream(regions.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        List<Distributor> distributors = new ArrayList<>();
        for (String region : regionList) {
            distributors.addAll(distributorRepository.findByRegion(region));
        }

        // Remove duplicates by code and return
        return distributors.stream()
                .collect(Collectors.toMap(
                        Distributor::getDbCode,
                        d -> d,
                        (existing, replacement) -> existing  // Keep first occurrence
                ))
                .values()
                .stream()
                .map(d -> Map.of("name", d.getDbName(), "code", d.getDbCode()))
                .sorted((a, b) -> a.get("name").compareTo(b.get("name")))  // Sort by name
                .collect(Collectors.toList());
    }

    // Distributors by single region
    @GetMapping("/by-region")
    public List<Map<String, String>> getDistributorsByRegion(@RequestParam String region) {
        return distributorRepository.findByRegion(region)
                .stream()
                .map(d -> Map.of("name", d.getDbName(), "code", d.getDbCode()))
                .collect(Collectors.toList());
    }

    // Distributors by region + area
    @GetMapping("/by-region-area")
    public List<Map<String, String>> getDistributorsByRegionAndArea(@RequestParam String region,
                                                                    @RequestParam String area) {
        return distributorRepository.findByRegionAndArea(region, area)
                .stream()
                .map(d -> Map.of("name", d.getDbName(), "code", d.getDbCode()))
                .collect(Collectors.toList());
    }

    // ✅ NEW: Search distributors by keyword (across all user's regions)
    @GetMapping("/search")
    public List<Map<String, String>> searchDistributors(
            @RequestParam String regions,
            @RequestParam(required = false, defaultValue = "") String keyword) {

        // Get all distributors for user's regions
        List<String> regionList = Arrays.stream(regions.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        List<Distributor> distributors = new ArrayList<>();
        for (String region : regionList) {
            distributors.addAll(distributorRepository.findByRegion(region));
        }

        // Remove duplicates and filter by keyword
        return distributors.stream()
                .collect(Collectors.toMap(
                        Distributor::getDbCode,
                        d -> d,
                        (existing, replacement) -> existing
                ))
                .values()
                .stream()
                .filter(d -> keyword.isEmpty() ||
                        d.getDbName().toLowerCase().contains(keyword.toLowerCase()) ||
                        d.getDbCode().toLowerCase().contains(keyword.toLowerCase()))
                .map(d -> Map.of("name", d.getDbName(), "code", d.getDbCode()))
                .sorted((a, b) -> a.get("name").compareTo(b.get("name")))
                .collect(Collectors.toList());
    }

    // ✅ NEW: Get territories from multiple regions (comma-separated)
    @GetMapping("/territories/by-regions")
    public Set<String> getTerritoriesByRegions(@RequestParam String regions) {
        // Split comma-separated regions: "Western, Central, Southern"
        List<String> regionList = Arrays.stream(regions.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        Set<String> allTerritories = new java.util.HashSet<>();
        for (String region : regionList) {
            List<Distributor> distributors = distributorRepository.findByRegion(region);
            distributors.forEach(d -> allTerritories.addAll(d.getTerritories()));
        }

        return allTerritories;
    }

    // Territories by region
    @GetMapping("/territories/by-region")
    public Set<String> getTerritoriesByRegion(@RequestParam String region) {
        return distributorRepository.findByRegion(region)
                .stream()
                .flatMap(d -> d.getTerritories().stream())
                .collect(Collectors.toSet());
    }

    // Territories by region + area
    @GetMapping("/territories/by-region-area")
    public Set<String> getTerritoriesByRegionAndArea(@RequestParam String region,
                                                     @RequestParam String area) {
        return distributorRepository.findByRegionAndArea(region, area)
                .stream()
                .flatMap(d -> d.getTerritories().stream())
                .collect(Collectors.toSet());
    }
}
