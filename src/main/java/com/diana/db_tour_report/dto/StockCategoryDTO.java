package com.diana.db_tour_report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public class StockCategoryDTO {

    private String id; // Frontend ID (e.g., "cat_123")

    @NotBlank(message = "Category name is required")
    @Size(max = 100, message = "Category name cannot exceed 100 characters")
    private String name;

    @Valid
    @NotNull(message = "Items are required")
    @Size(min = 1, message = "At least one item is required")
    private List<StockItemDTO> items;

    // Optional comment for the whole category (e.g., "Shortage due to transport")
    private String comment;

    // ── Constructors ───────────────────────────────
    public StockCategoryDTO() {}

    // ── Getters and Setters ────────────────────────
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<StockItemDTO> getItems() { return items; }
    public void setItems(List<StockItemDTO> items) { this.items = items; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}