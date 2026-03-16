package com.diana.db_tour_report.service;

import com.diana.db_tour_report.dto.*;
import com.diana.db_tour_report.entity.*;
import com.diana.db_tour_report.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MarketExecutionService {

    private final MarketReviewRepository reviewRepo;
    private final OutletRepository outletRepo;

    public MarketExecutionService(MarketReviewRepository reviewRepo, OutletRepository outletRepo) {
        this.reviewRepo = reviewRepo;
        this.outletRepo = outletRepo;
    }

    public void submitFullReport(MarketExecutionRequest request) {
        String sharedKey = request.getReportGroupId();

        // 1. Save Performance Reviews
        if (request.getReviews() != null) {
            List<MarketReview> reviews = request.getReviews().stream().map(dto -> {
                MarketReview review = new MarketReview();
                review.setReportGroupId(sharedKey);
                review.setRegion(request.getRegion());
                review.setTerritoryName(request.getTerritoryName());
                review.setRouteName(request.getRouteName());
                review.setArea(dto.getArea());
                review.setObservation(dto.getObservation());
                review.setImageBase64(dto.getImageBase64());
                review.setUserName(request.getUserName());
                review.setUserRole(request.getUserRole());
                return review;
            }).collect(Collectors.toList());
            reviewRepo.saveAll(reviews);
        }

        // 2. Save Mega Outlets
        if (request.getOutlets() != null) {
            List<MegaOutlet> outlets = request.getOutlets().stream().map(dto -> {
                MegaOutlet outlet = new MegaOutlet();
                outlet.setReportGroupId(sharedKey);
                outlet.setRegion(request.getRegion());
                outlet.setTerritoryName(request.getTerritoryName());
                outlet.setRouteName(request.getRouteName());
                outlet.setName(dto.getName());
                outlet.setSales(dto.getSales());
                outlet.setDiscount(dto.getDiscount());
                outlet.setSku(dto.getSku());
                outlet.setOutletImageBase64(dto.getOutletImageBase64());
                outlet.setUserName(request.getUserName());
                outlet.setUserRole(request.getUserRole());
                return outlet;
            }).collect(Collectors.toList());
            outletRepo.saveAll(outlets);
        }
    }
}