package com.teembinsys.core.healthcare.model;


import com.teembinsys.core.healthcare.util.CommonUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "doctor_daily_appointment_summary",
        uniqueConstraints = @UniqueConstraint(columnNames = {"doctor_id", "summary_date"}))
@Getter
@Setter
public class DoctorDailyAppointmentSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_summary")
    private Long appointmentSummaryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "summary_date", nullable = false)
    private LocalDateTime summaryDate = CommonUtils.truncateToSeconds(LocalDateTime.now());

    @Column(name = "total_booked")
    private int totalBooked = 0;

    @Column(name = "total_cancelled")
    private int totalCancelled = 0;

    @Column(name = "total_rescheduled")
    private int totalRescheduled = 0;

    @Column(name = "total_no_show")
    private int totalNoShow = 0;

    @Column(name = "total_reopen")
    private int totalReopen = 0;

    @Column(name = "total_completed")
    private int totalCompleted = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt = CommonUtils.truncateToSeconds(LocalDateTime.now());

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = CommonUtils.truncateToSeconds(LocalDateTime.now());
}
