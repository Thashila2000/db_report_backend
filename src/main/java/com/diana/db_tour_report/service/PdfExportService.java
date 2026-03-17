package com.diana.db_tour_report.service;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.borders.Border;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.itextpdf.layout.borders.SolidBorder;

import com.diana.db_tour_report.repository.*;
import com.diana.db_tour_report.entity.*;

@Service
public class PdfExportService {

    @Autowired private ReportActionRepository reportRepo;
    @Autowired private StockItemRepository stockRepo;
    @Autowired private ActionRepository actionRepo;
    @Autowired private IssueRepository issueRepo;
    @Autowired private DBProfileRepository profileRepo;
    @Autowired private RemarksRepository remarksRepo;
    @Autowired private SalesPerformanceRepository salesRepo;
    @Autowired private ActionStaffRepository staffRepo;
    @Autowired private FollowUpRepository followUpRepo;
    @Autowired private VisitDetailsRepository visitRepo;

    public byte[] generateFullReport(String reportGroupId) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // GLOBAL FONT CONSTANTS
            float normalSize = 10f;
            float topicSize = 12f;
            float titleSize = 18f;
            document.setFontSize(normalSize);

            // Date Formatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");



          // --- 1. VISIT DETAILS (HEADER) ---
            var visit = visitRepo.findByReportGroupId(reportGroupId).orElse(null);
            if (visit != null) {
                // Main Report Title
                document.add(new Paragraph("DB TOUR VISIT REPORT")
                        .setBold().setFontSize(titleSize).setTextAlignment(TextAlignment.CENTER).setFontColor(ColorConstants.DARK_GRAY));

                document.add(new Paragraph("Report ID: " + reportGroupId)
                        .setFontSize(8).setTextAlignment(TextAlignment.RIGHT).setMarginBottom(10));

                // --- SECTION TOPIC ---
                document.add(new Paragraph("1. Visit Details")
                        .setBold().setFontSize(topicSize).setUnderline());

                // Force table to 100% width
                Table headerTable = new Table(UnitValue.createPercentArray(new float[]{55, 45})).useAllAvailableWidth();

                // Date and Time Formatting
                String dateStr = "N/A";
                String timeStr = "N/A";
                if (visit.getVisitTime() != null) {
                    dateStr = visit.getVisitTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy.MM.dd"));
                    timeStr = visit.getVisitTime().format(java.time.format.DateTimeFormatter.ofPattern("H.mm"));
                }

                // Row 1: Distributor (Left) & Date/Time (Right)
                headerTable.addCell(createNoBorderCell("Distributor: " + visit.getDbName() + " (" + visit.getDbCode() + ")", normalSize).setBold());
                headerTable.addCell(createNoBorderCell("Date: " + dateStr + "   Time: " + timeStr, normalSize).setTextAlignment(TextAlignment.RIGHT));

                // Row 2: Territory (Left) & Region (Right)
                headerTable.addCell(createNoBorderCell("Territory: " + visit.getTerritoryName(), normalSize));
                headerTable.addCell(createNoBorderCell("Region: " + visit.getRegion(), normalSize).setTextAlignment(TextAlignment.RIGHT));

                // Row 3: Visited By (Left) & Area (Right)
                headerTable.addCell(createNoBorderCell("Visited By: " + visit.getVisitedBy(), normalSize));
                headerTable.addCell(createNoBorderCell("Area: " + visit.getArea(), normalSize).setTextAlignment(TextAlignment.RIGHT));

                // Row 4: Accompanied By (Left) & Visit Type (Right)
                headerTable.addCell(createNoBorderCell("Accompanied By: " + visit.getAccompaniedBy(), normalSize));
                headerTable.addCell(createNoBorderCell("Visit Type: " + visit.getVisitType(), normalSize).setTextAlignment(TextAlignment.RIGHT));

                // Row 5: RSM (Left) & Empty (Right)
                headerTable.addCell(createNoBorderCell("RSM: " + visit.getUserName(), normalSize));
                headerTable.addCell(createNoBorderCell("", normalSize));

                document.add(headerTable);

                // SECTION FOR ACCOMPANIED BY IMAGE
                if (visit.getAccompaniedByImage() != null && !visit.getAccompaniedByImage().isEmpty()) {
                    try {
                        // Remove the Base64 header if present (e.g., "data:image/jpeg;base64,")
                        String base64Data = visit.getAccompaniedByImage().contains(",")
                                ? visit.getAccompaniedByImage().split(",")[1]
                                : visit.getAccompaniedByImage();

                        byte[] imageBytes = Base64.getDecoder().decode(base64Data);
                        Image img = new Image(ImageDataFactory.create(imageBytes));

                        // Auto-scale to fit page width while maintaining aspect ratio
                        img.setMaxWidth(180);
                        img.setMarginTop(10);
                        img.setBorder(new com.itextpdf.layout.borders.SolidBorder(ColorConstants.LIGHT_GRAY, 1));

                        document.add(new Paragraph("Proof of Visit:").setFontSize(10).setItalic().setMarginTop(5));
                        document.add(img);
                    } catch (Exception e) {
                        document.add(new Paragraph("[Image Error: Could not render visit photo]")
                                .setFontSize(8).setFontColor(ColorConstants.RED));
                    }
                }

                document.add(new Paragraph("\n"));
            }
            // --- 2. DB PROFILE & INFRASTRUCTURE ---
            profileRepo.findByReportGroupId(reportGroupId).ifPresent(profile -> {
                // 2.1 BASIC PROFILE
                document.add(new Paragraph("\n2. DB PROFILE & INFRASTRUCTURE")
                        .setBold().setFontSize(topicSize).setUnderline().setMarginBottom(5));

                Table genTable = new Table(UnitValue.createPercentArray(new float[]{50, 50})).useAllAvailableWidth();
                genTable.addCell(createNoBorderCell("• Owner: " + profile.getDbOwnerContact(), normalSize));
                genTable.addCell(createNoBorderCell("• Coverage: " + profile.getCoverageArea(), normalSize));
                genTable.addCell(createNoBorderCell("• Routes: " + profile.getRouteStrength(), normalSize));
                genTable.addCell(createNoBorderCell("• Sales Team: " + profile.getSalesTeam(), normalSize));
                genTable.addCell(createNoBorderCell("• Vehicles: " + profile.getVehicleAvailability(), normalSize));
                genTable.addCell(createNoBorderCell("• Log Book: " + profile.getLogBookUpdate(), normalSize));
                document.add(genTable);

                // ROUTE VISUALS
                Table imageTable = new Table(UnitValue.createPercentArray(new float[]{48, 4, 48})).useAllAvailableWidth().setMarginTop(10);
                Cell mapCell = new Cell().add(new Paragraph("Route Map: " + profile.getTerritoryRouteMap()).setFontSize(9).setBold()).setBorder(Border.NO_BORDER);
                if (hasImage(profile.getRouteMapImage())) addSmallImageToCell(mapCell, profile.getRouteMapImage());
                imageTable.addCell(mapCell);
                imageTable.addCell(new Cell().setBorder(Border.NO_BORDER));
                Cell planCell = new Cell().add(new Paragraph("Route Plan: " + profile.getRoutePlan()).setFontSize(9).setBold()).setBorder(Border.NO_BORDER);
                if (hasImage(profile.getRoutePlanImage())) addSmallImageToCell(planCell, profile.getRoutePlanImage());
                imageTable.addCell(planCell);
                document.add(imageTable);

                // --- 3. FINANCIAL SUMMARY ---
                document.add(new Paragraph("\n FINANCIAL SUMMARY").setBold().setFontSize(11));
                Table finTable = new Table(UnitValue.createPercentArray(new float[]{70, 30})).useAllAvailableWidth();
                finTable.addCell(createNoBorderCell("Credit Bills Total (" + profile.getCreditBillCount() + " bills)", normalSize));
                finTable.addCell(createNoBorderCell("LKR " + formatCurrency(profile.getCreditBillTotal()), normalSize).setTextAlignment(TextAlignment.RIGHT));
                finTable.addCell(createNoBorderCell("Post Dated Cheques (" + profile.getChequeCount() + " cheques)", normalSize));
                finTable.addCell(createNoBorderCell("LKR " + formatCurrency(profile.getChequeTotal()), normalSize).setTextAlignment(TextAlignment.RIGHT));
                finTable.addCell(createNoBorderCell("Total Cash in Hand+Bank", normalSize));
                finTable.addCell(createNoBorderCell("LKR " + formatCurrency(profile.getCashTotal()), normalSize).setTextAlignment(TextAlignment.RIGHT));
                document.add(finTable);

                // --- 4. PROGRESS & SKU PERFORMANCE ---
                document.add(new Paragraph("\n PROGRESS & SKU PERFORMANCE").setBold().setFontSize(11));

                // Using explicit iText List to avoid naming conflict with java.util.List
                com.itextpdf.layout.element.List skuList = new com.itextpdf.layout.element.List().setSymbolIndent(10).setListSymbol("• ");
                skuList.add(new ListItem("Progress Sheet Update Status: " + profile.getProgressSheetUpdate()));
                skuList.add(new ListItem("SKU Sales Performance: " + (profile.getSkuSalesUpdate() != null ? profile.getSkuSalesUpdate().toUpperCase() : "N/A")));
                document.add(skuList);

                document.add(new Paragraph("SKU Sales Observations:").setBold().setFontSize(9).setMarginTop(5));
                document.add(new Paragraph(profile.getSkuSalesComment() != null && !profile.getSkuSalesComment().isEmpty() ? profile.getSkuSalesComment() : "No specific SKU observations noted.")
                        .setFontSize(normalSize).setItalic()
                        .setPadding(8).setMultipliedLeading(1.4f)
                        .setBorder(new SolidBorder(ColorConstants.LIGHT_GRAY, 0.5f)));

                // --- 5. STORE & WAREHOUSE AUDIT ---
                document.add(new Paragraph("\nSTORE / WAREHOUSE ").setBold().setFontSize(11));

                // 5. Storage & Conditions Grid
                Table storeGrid = new Table(UnitValue.createPercentArray(new float[]{33, 33, 34})).useAllAvailableWidth();

// Added "ft" to both Length and Width for clarity
                String dimensions = "• Dimensions: " + profile.getStoreLength() + " ft x " + profile.getStoreWidth() + " ft";

                storeGrid.addCell(createNoBorderCell(dimensions, normalSize));
                storeGrid.addCell(createNoBorderCell("• Table Count: " + profile.getTableCount(), normalSize));
                storeGrid.addCell(createNoBorderCell("• Chair Count: " + profile.getChairCount(), normalSize));

                document.add(storeGrid);

                document.add(new Paragraph("\nStore & Return Conditions:").setBold().setFontSize(9));
                com.itextpdf.layout.element.List conditionList = new com.itextpdf.layout.element.List().setSymbolIndent(10).setListSymbol("• ");

                ListItem storeCondItem = new ListItem();
                storeCondItem.add(new Paragraph("Store Condition: ").setBold().setFontSize(normalSize));
                storeCondItem.add(new Paragraph(profile.getStoreCondition()).setMultipliedLeading(1.3f));
                conditionList.add(storeCondItem);

                ListItem returnCondItem = new ListItem();
                returnCondItem.add(new Paragraph("Market Return Condition: ").setBold().setFontSize(normalSize));
                returnCondItem.add(new Paragraph(profile.getMarketReturnCondition()).setMultipliedLeading(1.3f));
                conditionList.add(returnCondItem);
                document.add(conditionList);

                document.add(new Paragraph("Additional Store / Warehouse Comments:").setBold().setFontSize(9).setMarginTop(8));
                document.add(new Paragraph(profile.getStoreComments() != null && !profile.getStoreComments().isEmpty() ? profile.getStoreComments() : "No additional warehouse observations.")
                        .setFontSize(normalSize).setItalic()
                        .setPadding(10).setMultipliedLeading(1.5f)
                        .setBorder(new SolidBorder(ColorConstants.GRAY, 0.5f))
                        .setBackgroundColor(ColorConstants.WHITE));
            });

            // --- 3. SALES PERFORMANCE ---
            var performance = salesRepo.findAllByReportGroupId(reportGroupId);
            if (performance != null && !performance.isEmpty()) {
                document.add(new Paragraph("\n4. SALES PERFORMANCE (MTD)").setBold().setFontSize(topicSize).setUnderline());
                Table salesTable = new Table(UnitValue.createPercentArray(new float[]{25, 15, 15, 15, 30})).useAllAvailableWidth();
                addTableHeader(salesTable, new String[]{"Category", "Target", "Achieved", "Variance", "Remarks"}, normalSize);
                for (var s : performance) {
                    salesTable.addCell(createNormalCell(s.getCategory(), normalSize));
                    salesTable.addCell(createNormalCell(s.getMtdTarget(), normalSize));
                    salesTable.addCell(createNormalCell(s.getMtdAchieved(), normalSize));
                    salesTable.addCell(createNormalCell(s.getVariance(), normalSize));
                    salesTable.addCell(createNormalCell(s.getRemarks(), normalSize));
                }
                document.add(salesTable);
            }

            // --- 4. STOCK STATUS (GROUPED BY CATEGORY) ---
            var stocks = stockRepo.findAllByReportGroupId(reportGroupId);
            if (stocks != null && !stocks.isEmpty()) {
                document.add(new Paragraph("\n5. STOCK STATUS").setBold().setFontSize(topicSize).setUnderline().setMarginBottom(10));

                Map<String, List<StockItem>> groupedStocks = new LinkedHashMap<>();
                for (var s : stocks) {
                    groupedStocks.computeIfAbsent(s.getCategoryName(), k -> new java.util.ArrayList<>()).add(s);
                }

                for (var entry : groupedStocks.entrySet()) {
                    String category = entry.getKey();
                    List<StockItem> items = entry.getValue();

                    document.add(new Paragraph("Category: " + category).setBold().setFontSize(11).setMarginTop(10));
                    Table stockTable = new Table(UnitValue.createPercentArray(new float[]{40, 20, 20, 20})).useAllAvailableWidth();
                    addTableHeader(stockTable, new String[]{"Item", "System", "Physical", "Variance"}, normalSize);

                    String categoryComment = "";
                    for (var s : items) {
                        stockTable.addCell(createNormalCell(s.getItemName(), normalSize));
                        stockTable.addCell(createNormalCell(s.getSystemStock(), normalSize));
                        stockTable.addCell(createNormalCell(s.getStockLevel(), normalSize));
                        stockTable.addCell(createNormalCell(s.getVariance(), normalSize));
                        if (s.getCategoryComment() != null) categoryComment = s.getCategoryComment();
                    }
                    document.add(stockTable);

                    if (!categoryComment.isEmpty()) {
                        document.add(new Paragraph("Observation for " + category + ": " + categoryComment)
                                .setFontSize(10).setItalic().setMarginBottom(10).setPaddingLeft(5));
                    }
                }
            }

            // --- 5. ISSUES IDENTIFIED (TABLE) ---
            var issues = issueRepo.findAllByReportGroupId(reportGroupId);
            if (issues != null && !issues.isEmpty()) {
                document.add(new Paragraph("\n6. ISSUES IDENTIFIED").setBold().setFontSize(topicSize).setUnderline().setMarginBottom(8));
                Table issueTable = new Table(UnitValue.createPercentArray(new float[]{20, 60, 20})).useAllAvailableWidth();
                addTableHeader(issueTable, new String[]{"Issue Type", "Description", "Security"}, normalSize);
                for (var i : issues) {
                    issueTable.addCell(createNormalCell(i.getIssueType(), normalSize));
                    issueTable.addCell(createNormalCell(i.getDescription(), normalSize));
                    issueTable.addCell(createNormalCell(i.getSecurity(), normalSize));
                }
                document.add(issueTable);
            }

            // --- 6. CORRECTIVE ACTIONS ---
            var actions = actionRepo.findAllByReportGroupId(reportGroupId);
            if (actions != null && !actions.isEmpty()) {
                document.add(new Paragraph("\n7. CORRECTIVE ACTIONS (DB AGREEMENT)").setBold().setFontSize(topicSize).setUnderline().setMarginTop(10).setMarginBottom(8));
                Table actTable = new Table(UnitValue.createPercentArray(new float[]{50, 50})).useAllAvailableWidth();
                addTableHeader(actTable, new String[]{"Action Required", "DB Comment"}, normalSize);
                for (var a : actions) {
                    actTable.addCell(createNormalCell(a.getAction(), normalSize));
                    actTable.addCell(createNormalCell(a.getComment(), normalSize));
                }
                document.add(actTable);
            }

            // --- 7. STAFF FEEDBACK ---
            var staff = staffRepo.findAllByReportGroupId(reportGroupId);
            if (staff != null && !staff.isEmpty()) {
                document.add(new Paragraph("\n8. Staff Feedback & Agreed Actions")
                        .setBold().setFontSize(topicSize).setUnderline().setMarginBottom(8));

                // Define 3 columns: Designation (20%), Name (25%), Comment (55%)
                Table stTable = new Table(UnitValue.createPercentArray(new float[]{20, 25, 55})).useAllAvailableWidth();

                // Add Table Headers
                addTableHeader(stTable, new String[]{"Designation", "Staff Name", "Comment / Action"}, normalSize);

                for (var f : staff) {
                    // Designation Column (CSR, ASE, ASM, etc.)
                    stTable.addCell(createNormalCell(f.getPosition(), normalSize).setBold());

                    // Name Column
                    stTable.addCell(createNormalCell(f.getName(), normalSize));

                    // Comment Column with multiplied leading for extra space
                    Paragraph commentPara = new Paragraph(f.getComment() != null ? f.getComment() : "—")
                            .setFontSize(normalSize)
                            .setMultipliedLeading(1.2f); // Adds subtle spacing between lines for readability

                    stTable.addCell(new Cell().add(commentPara).setPadding(5));
                }

                document.add(stTable);
            }

            // --- 8. FOLLOW UP & FINAL REMARKS ---
            var followUps = followUpRepo.findAllByReportGroupId(reportGroupId);
            if (followUps != null && !followUps.isEmpty()) {
                document.add(new Paragraph("\n9. FOLLOW-UP PLAN").setBold().setFontSize(topicSize).setUnderline());
                Table fuTable = new Table(UnitValue.createPercentArray(new float[]{50, 25, 25})).useAllAvailableWidth();
                addTableHeader(fuTable, new String[]{"Action", "Responsible", "Deadline"}, normalSize);
                for (var fu : followUps) {
                    fuTable.addCell(createNormalCell(fu.getAction(), normalSize));
                    fuTable.addCell(createNormalCell(fu.getResponsible(), normalSize));
                    fuTable.addCell(createNormalCell(fu.getDeadline(), normalSize));
                }
                document.add(fuTable);
            }


            // --- 8. FINAL REMARKS & SIGNATURES ---
            var remarks = remarksRepo.findByReportGroupId(reportGroupId);
            if (remarks != null) {
                document.add(new Paragraph("\n10. FINAL SUMMARY").setBold().setFontSize(topicSize).setUnderline());
                document.add(new Paragraph(remarks.getRemarks())
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY, 0.2f)
                        .setPadding(8)
                        .setFontSize(normalSize));
            }

            // Space before signatures
            document.add(new Paragraph("\n\n\n"));

            // Create a 2-column table for signatures (50% | 50%)
            Table signTable = new Table(UnitValue.createPercentArray(new float[]{50, 50})).useAllAvailableWidth();

            // --- LEFT COLUMN: Prepared By (RSM) ---
            Cell leftCell = new Cell().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER);
            leftCell.add(new Paragraph("..................................................")
                    .setMarginBottom(0));
            leftCell.add(new Paragraph("Prepared By")
                    .setBold().setFontSize(9).setMarginBottom(0));
            if (remarks != null) {
                leftCell.add(new Paragraph(remarks.getPreparedBy() + " (" + (remarks.getUserRole() != null ? remarks.getUserRole() : "N/A") + ")")
                        .setFontSize(8).setItalic());
            }
            signTable.addCell(leftCell);

            // --- RIGHT COLUMN: Distributor Approval ---
            Cell rightCell = new Cell().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER);
            rightCell.add(new Paragraph("..................................................")
                    .setMarginBottom(0));
            rightCell.add(new Paragraph("Distributor Acknowledgment")
                    .setBold().setFontSize(9).setMarginBottom(0));
            rightCell.add(new Paragraph("Signature")
                    .setFontSize(8).setItalic());
            signTable.addCell(rightCell);

            document.add(signTable);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    // --- HELPERS ---

    private void addTableHeader(Table table, String[] headers, float size) {
        for (String h : headers) {
            table.addHeaderCell(new Cell().add(new Paragraph(h).setBold().setFontSize(size)));
        }
    }

    private Cell createNormalCell(String text, float size) {
        return new Cell().add(new Paragraph(text != null ? text : "").setFontSize(size));
    }

    private Cell createNoBorderCell(String text, float size) {
        return new Cell().add(new Paragraph(text != null ? text : "").setFontSize(size)).setBorder(Border.NO_BORDER);
    }

    private void addSmallImageToCell(Cell cell, String base64Image) {
        try {
            String cleanBase64 = base64Image.contains(",") ? base64Image.split(",")[1] : base64Image;
            byte[] bytes = Base64.getDecoder().decode(cleanBase64.trim());
            Image img = new Image(ImageDataFactory.create(bytes)).setAutoScale(true).setMaxHeight(80f);
            cell.add(img);
        } catch (Exception e) {
            cell.add(new Paragraph("[Image Error]").setFontSize(8));
        }
    }

    private String formatCurrency(String value) {
        try {
            if (value == null || value.isEmpty()) return "0.00";
            return String.format("%,.2f", Double.parseDouble(value.replace(",", "")));
        } catch (Exception e) { return value; }
    }

    private boolean hasImage(String base64) {
        return base64 != null && base64.length() > 100;
    }
}