package com.diana.db_tour_report.controller;

import com.diana.db_tour_report.dto.OutletDTO;
import com.diana.db_tour_report.entity.MegaOutlet;
import com.diana.db_tour_report.service.OutletService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/outlets")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OutletController {

    private final OutletService service;

    public OutletController(OutletService service) {
        this.service = service;
    }

    @PostMapping
    public MegaOutlet save(@RequestBody OutletDTO dto) {
        return service.save(dto);
    }
}
