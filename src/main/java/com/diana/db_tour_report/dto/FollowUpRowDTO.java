package com.diana.db_tour_report.dto;

import jakarta.validation.constraints.NotBlank;

public class FollowUpRowDTO {

    private String id; // ✅ Frontend ID (e.g., f1, f2) for list mapping

    @NotBlank(message = "Action is required")
    private String action;

    @NotBlank(message = "Responsible person/department is required")
    private String responsible;

    @NotBlank(message = "Deadline is required")
    private String deadline;

    // Default constructor for Jackson
    public FollowUpRowDTO() {}

    public FollowUpRowDTO(String id, String action, String responsible, String deadline) {
        this.id = id;
        this.action = action;
        this.responsible = responsible;
        this.deadline = deadline;
    }

    // --- Getters and Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getResponsible() { return responsible; }
    public void setResponsible(String responsible) { this.responsible = responsible; }

    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }
}