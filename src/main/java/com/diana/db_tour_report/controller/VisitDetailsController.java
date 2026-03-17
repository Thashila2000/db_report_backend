package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.VisitDetailsDTO;
import com.diana.db_tour_report.entity.VisitDetails;
import com.diana.db_tour_report.service.VisitDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visit-details")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class VisitDetailsController {

    private final VisitDetailsService visitService;

    public VisitDetailsController(VisitDetailsService visitService) {
        this.visitService = visitService;
    }

    // ✅ CREATE: Starts a new tour report entry
    @PostMapping
    public ResponseEntity<VisitDetails> saveVisitDetails(@Valid @RequestBody VisitDetailsDTO dto) {
        VisitDetails savedVisit = visitService.saveVisitDetails(dto);
        return ResponseEntity.ok(savedVisit);
    }

    // ✅ READ: Fetch history for the user dashboard (Sorted by newest first)
    @GetMapping("/user/{userName}")
    public ResponseEntity<List<VisitDetails>> getUserVisitHistory(@PathVariable String userName) {
        return ResponseEntity.ok(visitService.findByUserNameSorted(userName));
    }

    // ✅ READ: Fetch specific header by its Group ID
    @GetMapping("/report/{reportGroupId}")
    public ResponseEntity<VisitDetails> getByGroupId(@PathVariable String reportGroupId) {
        return visitService.findByReportGroupId(reportGroupId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ DELETE: Optional - If a user needs to discard a draft/entry
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        visitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}