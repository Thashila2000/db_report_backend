package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.FieldVisitReportDTO;
import com.diana.db_tour_report.dto.ReportSummaryDTO;
import com.diana.db_tour_report.entity.MarketReview;
import com.diana.db_tour_report.entity.MegaOutlet;
import com.diana.db_tour_report.repository.MarketReviewRepository;
import com.diana.db_tour_report.repository.OutletRepository;
import com.diana.db_tour_report.service.ReportService;
import com.diana.db_tour_report.service.PdfExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:5173")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private PdfExportService pdfExportService;

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private MarketReviewRepository marketReviewRepository;

    /**
     * FIXED: Added (required = false) to userName and added region parameter
     */
    @GetMapping("/daily-tasks")
    public ResponseEntity<List<ReportSummaryDTO>> getDailyTasks(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String date) {

        // If region is provided, use Admin logic
        if (region != null && !region.isEmpty()) {
            return ResponseEntity.ok(reportService.getReportsByRegionAndType(region, date, "DAILY_TASK"));
        }
        // Fallback to User logic (requires userName)
        return ResponseEntity.ok(reportService.getFilteredReports(userName, date, "DAILY_TASK"));
    }

    /**
     * FIXED: Added (required = false) to userName and added region parameter
     */
    @GetMapping("/field-visits")
    public ResponseEntity<List<ReportSummaryDTO>> getVisitDetails(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String date) {

        // If region is provided, use Admin logic
        if (region != null && !region.isEmpty()) {
            return ResponseEntity.ok(reportService.getReportsByRegionAndType(region, date, "FIELD_VISIT"));
        }
        // Fallback to User logic
        return ResponseEntity.ok(reportService.getFilteredReports(userName, date, "FIELD_VISIT"));
    }

    @GetMapping("/full-summary/{reportGroupId}")
    public ResponseEntity<Map<String, Object>> getFullSummary(@PathVariable String reportGroupId) {
        try {
            Map<String, Object> summary = reportService.getConsolidatedFullReport(reportGroupId);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/export-pdf/{reportGroupId}")
    public ResponseEntity<byte[]> exportPdf(@PathVariable String reportGroupId) {
        try {
            byte[] pdfContents = pdfExportService.generateFullReport(reportGroupId);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Report_" + reportGroupId + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfContents);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping({"/save-visit", "/save-field"})
    public ResponseEntity<String> saveReport(@RequestBody ReportSummaryDTO dto) {
        try {
            reportService.saveVisitReport(dto);
            return ResponseEntity.ok("Saved Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/field-visit-details/{reportGroupId}")
    public ResponseEntity<FieldVisitReportDTO> getFieldVisitDetails(@PathVariable String reportGroupId) {
        List<MegaOutlet> outlets = outletRepository.findByReportGroupId(reportGroupId);
        List<MarketReview> reviews = marketReviewRepository.findByReportGroupId(reportGroupId);
        return ResponseEntity.ok(new FieldVisitReportDTO(outlets, reviews));
    }

    @GetMapping("/admin/region-summary")
    public ResponseEntity<Map<String, Object>> getRegionSummary(
            @RequestParam String region,
            @RequestParam(required = false) String date) {

        String rsmName = reportService.getRSMForRegion(region);
        List<ReportSummaryDTO> reports = reportService.getReportsByRegionAndType(region, date, "DAILY_TASK");

        Map<String, Object> response = new HashMap<>();
        response.put("rsmName", rsmName);
        response.put("region", region);
        response.put("reports", reports);

        return ResponseEntity.ok(response);
    }
}