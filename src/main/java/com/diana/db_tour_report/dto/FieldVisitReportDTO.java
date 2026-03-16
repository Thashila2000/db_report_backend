package com.diana.db_tour_report.dto;

import com.diana.db_tour_report.entity.MegaOutlet;
import com.diana.db_tour_report.entity.MarketReview;
import java.util.List;

public class FieldVisitReportDTO {
    private List<MegaOutlet> outlets;
    private List<MarketReview> reviews;

    // Default Constructor
    public FieldVisitReportDTO() {}

    // Parameterized Constructor
    public FieldVisitReportDTO(List<MegaOutlet> outlets, List<MarketReview> reviews) {
        this.outlets = outlets;
        this.reviews = reviews;
    }

    // --- Getters and Setters ---
    public List<MegaOutlet> getOutlets() {
        return outlets;
    }

    public void setOutlets(List<MegaOutlet> outlets) {
        this.outlets = outlets;
    }

    public List<MarketReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<MarketReview> reviews) {
        this.reviews = reviews;
    }
}