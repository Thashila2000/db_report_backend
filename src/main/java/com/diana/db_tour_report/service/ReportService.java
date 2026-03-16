package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.ReportSummaryDTO;
import com.diana.db_tour_report.entity.*;
import com.diana.db_tour_report.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired private VisitDetailsRepository visitRepo;
    @Autowired private DBProfileRepository profileRepo;
    @Autowired private SalesPerformanceRepository salesRepo;
    @Autowired private StockItemRepository stockRepo;
    @Autowired private IssueRepository issueRepo;
    @Autowired private ActionRepository actionRepo;
    @Autowired private ActionStaffRepository staffRepo;
    @Autowired private FollowUpRepository followUpRepo;
    @Autowired private RemarksRepository remarksRepo;
    @Autowired private ReportActionRepository reportRepo;
    @Autowired private OutletRepository outletRepo;

    /**
     * User Page Filter (By UserName)
     */
    public List<ReportSummaryDTO> getFilteredReports(String userName, String dateStr, String type) {
        List<ReportSummaryDTO> resultList = new ArrayList<>();

        if ("DAILY_TASK".equalsIgnoreCase(type)) {
            List<ReportAction> dailyTasks;
            if (dateStr != null && !dateStr.trim().isEmpty()) {
                String dbPattern = dateStr.replace("-", ".");
                dailyTasks = reportRepo.findByUserNameAndCreatedAtStartingWith(userName, dbPattern);
            } else {
                String thirtyDaysAgo = LocalDate.now().minusDays(30).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                dailyTasks = reportRepo.findByUserNameAndCreatedAtGreaterThanEqualOrderByCreatedAtDesc(userName, thirtyDaysAgo);
            }
            resultList = dailyTasks.stream().map(this::mapTaskToDTO).collect(Collectors.toList());
        }
        else if ("FIELD_VISIT".equalsIgnoreCase(type)) {
            List<MegaOutlet> allOutlets = outletRepo.findByUserName(userName);
            resultList = processFieldVisitList(allOutlets, dateStr);
        }

        return sortDescending(resultList);
    }

    /**
     * Admin Dashboard Filter (By Region)
     * FIXED: Uses ContainingIgnoreCase to match "Western" with "Western Region"
     */
    public List<ReportSummaryDTO> getReportsByRegionAndType(String region, String dateStr, String type) {
        List<ReportSummaryDTO> resultList = new ArrayList<>();

        if ("DAILY_TASK".equalsIgnoreCase(type)) {
            List<ReportAction> tasks;
            if (dateStr != null && !dateStr.trim().isEmpty()) {
                // First try standard hyphen format, then legacy dot format
                tasks = reportRepo.findByRegionContainingIgnoreCaseAndCreatedAtStartingWith(region, dateStr);
                if (tasks.isEmpty()) {
                    tasks = reportRepo.findByRegionContainingIgnoreCaseAndCreatedAtStartingWith(region, dateStr.replace("-", "."));
                }
            } else {
                tasks = reportRepo.findByRegionContainingIgnoreCaseOrderByCreatedAtDesc(region);
            }
            resultList = tasks.stream().map(this::mapTaskToDTO).collect(Collectors.toList());
        }
        else if ("FIELD_VISIT".equalsIgnoreCase(type)) {
            // Updated to use the fuzzy matching for outlets as well
            List<MegaOutlet> allOutlets = outletRepo.findByRegionContainingIgnoreCase(region);
            resultList = processFieldVisitList(allOutlets, dateStr);
        }

        return sortDescending(resultList);
    }

    /**
     * Used for the Full Preview Modal
     */
    public Map<String, Object> getConsolidatedFullReport(String reportGroupId) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<VisitDetails> visitDetailsList = visitRepo.findAllByReportGroupId(reportGroupId);
            data.put("visitDetails", visitDetailsList.isEmpty() ? null : visitDetailsList.get(0));
            data.put("dbProfile", profileRepo.findByReportGroupId(reportGroupId));
            data.put("salesPerformance", salesRepo.findAllByReportGroupId(reportGroupId));
            data.put("stockItems", stockRepo.findAllByReportGroupId(reportGroupId));
            data.put("issues", issueRepo.findAllByReportGroupId(reportGroupId));
            data.put("actions", actionRepo.findAllByReportGroupId(reportGroupId));
            data.put("actionStaff", staffRepo.findAllByReportGroupId(reportGroupId));
            data.put("followUps", followUpRepo.findAllByReportGroupId(reportGroupId));

            Object remarksObj = remarksRepo.findByReportGroupId(reportGroupId);
            data.put("remarks", remarksObj != null ? remarksObj : Collections.emptyMap());

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Identifies the RSM based on the most recent activity in the region
     */
    public String getRSMForRegion(String region) {
        List<ReportAction> recent = reportRepo.findByRegionContainingOrderByCreatedAtDesc(region);
        if (!recent.isEmpty()) {
            return recent.get(0).getUserName();
        }
        return "No RSM Assigned";
    }

    /**
     * Groups separate outlet visits into a single report summary entry
     */
    private List<ReportSummaryDTO> processFieldVisitList(List<MegaOutlet> outlets, String dateStr) {
        Map<String, MegaOutlet> uniqueVisits = new HashMap<>();
        for (MegaOutlet outlet : outlets) {
            // Handle both dot and hyphen date formats for the filter
            String outletDate = outlet.getCreatedAt() != null ? outlet.getCreatedAt().toString().split("T")[0].replace(".", "-") : "";
            if (dateStr == null || dateStr.trim().isEmpty() || outletDate.startsWith(dateStr)) {
                uniqueVisits.putIfAbsent(outlet.getReportGroupId(), outlet);
            }
        }
        return uniqueVisits.values().stream().map(this::mapVisitToDTO).collect(Collectors.toList());
    }

    private List<ReportSummaryDTO> sortDescending(List<ReportSummaryDTO> list) {
        list.sort((a, b) -> {
            if (a.getVisitTime() == null || b.getVisitTime() == null) return 0;
            return b.getVisitTime().compareTo(a.getVisitTime());
        });
        return list;
    }

    private ReportSummaryDTO mapTaskToDTO(ReportAction action) {
        return new ReportSummaryDTO(
                action.getReportGroupId(),
                action.getCreatedAt(),
                action.getDbName(),
                action.getDbCode(),
                action.getRegion(),
                action.getArea(),
                action.getTerritoryName(),
                action.getUserName()
        );
    }

    private ReportSummaryDTO mapVisitToDTO(MegaOutlet outlet) {
        String timestamp = outlet.getCreatedAt() != null
                ? outlet.getCreatedAt().toString().replace("T", " ").substring(0, 16)
                : "N/A";

        return new ReportSummaryDTO(
                outlet.getReportGroupId(),
                timestamp,
                null,
                null,
                outlet.getRegion(),
                null,
                outlet.getTerritoryName(),
                outlet.getUserName()
        );
    }

    @Transactional
    public void saveVisitReport(ReportSummaryDTO dto) {
        ReportAction entity = new ReportAction();
        entity.setReportGroupId(dto.getReportGroupId());
        entity.setUserName(dto.getUserName());
        entity.setTerritoryName(dto.getTerritoryName());
        entity.setDbName(dto.getDbName());
        entity.setDbCode(dto.getDbCode());
        entity.setRegion(dto.getRegion());
        entity.setArea(dto.getArea());

        if (dto.getVisitTime() != null) {
            // Matches your DB's 2026.03.10 format
            entity.setCreatedAt(dto.getVisitTime().replace("-", "."));
        }
        reportRepo.save(entity);
    }
}