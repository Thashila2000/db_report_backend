package com.diana.db_tour_report.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "action_staff")
public class ActionStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ THE MASTER LINK: Groups staff comments by specific report visit
    @Column(name = "report_group_id", nullable = false, length = 150)
    private String reportGroupId;

    @Column(nullable = false)
    private String position; // ASM, ASE, or CSR

    private String name;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "created_at", updatable = false)
    //  Formats for your "Day-by-Day" sorting view: 2026.03.09 10.59
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- Getters and Setters ---
    public String getReportGroupId() { return reportGroupId; }
    public void setReportGroupId(String reportGroupId) { this.reportGroupId = reportGroupId; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}