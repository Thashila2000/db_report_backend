package com.diana.db_tour_report.repository;

import com.diana.db_tour_report.entity.Remarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemarksRepository extends JpaRepository<Remarks, Long> {

    // Returns a List because a user might add multiple follow-up remarks
    List<Remarks> findAllByReportGroupId(String reportGroupId);

    // Returns a single object for the PDF (gets the most recent if multiple exist)
    // Or simply use this if your logic strictly enforces one remark per reportGroupId
    Remarks findByReportGroupId(String reportGroupId);

    List<Remarks> findByUserNameOrderByCreatedAtDesc(String userName);




}