package com.diana.db_tour_report.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "follow_ups")
public class FollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ THE MASTER LINK: Groups follow-ups by the specific visit
    @Column(name = "report_group_id", nullable = false, length = 150)
    private String reportGroupId;

    @Column(name = "action", columnDefinition = "TEXT")
    private String action;

    @Column(name = "responsible", columnDefinition = "TEXT")
    private String responsible;

    @Column(name = "deadline", columnDefinition = "TEXT")
    private String deadline;

    @Column(name = "user_name", length = 100)
    private String userName;

    @Column(name = "user_role", length = 150)
    private String userRole;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm") // Formats for UI: 2026.03.09 15.25
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // --- Getters and Setters ---
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getResponsible() { return responsible; }
    public void setResponsible(String responsible) { this.responsible = responsible; }

    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}