package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.RemarksDTO;
import com.diana.db_tour_report.dto.RemarksResponse;
import com.diana.db_tour_report.service.RemarksService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/remarks")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class RemarksController {

    private final RemarksService service;

    public RemarksController(RemarksService service) {
        this.service = service;
    }

    @PostMapping
    public RemarksResponse saveRemarks(@RequestBody RemarksDTO dto) {
        return service.saveRemarks(dto);
    }

    // --- ADD THIS METHOD ---
    @GetMapping("/{reportGroupId}")
    public RemarksResponse getRemarks(@PathVariable String reportGroupId) {
        return service.getSingleRemarksByGroupId(reportGroupId);
    }


}
