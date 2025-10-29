package com.teembinsys.core.healthcare.model;

import com.teembinsys.core.healthcare.constant.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentId;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "appointment_booking_time")
    private LocalDateTime appointmentBookingTime;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    //@Lob
    @Column(columnDefinition = "TEXT", name="symptoms")
    private String symptoms;

    // getters and setters
//    public LocalDateTime getAppointmentTime() { return appointmentTime; }
//    public void setAppointmentTime(LocalDateTime appointmentTime) {
//        this.appointmentTime = DateTimeUtils.truncateToSeconds(appointmentTime);
//    }

//    public LocalDateTime getCreatedAt() { return createdAt; }
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = DateTimeUtils.truncateToSeconds(createdAt);
//    }

    // Newly added columns ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    @Column(name = "doctor_slot_id")
    private Long doctorSlotId;

    @Column(name = "patient_slot_id")
    private String patientSlotId;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "status_change_reason", columnDefinition = "TEXT")
    private String statusChangeReason;

    @Column(name = "appointment_number")
    private String appointmentNumber;

    // 🆕 Added: Token number for patient sequence
    @Column(name = "patient_seq_token", length = 50, unique = true)
    private String patientSequenceToken;




    // Optional: truncate date-time before saving (good practice)
    // @PrePersist
    // @PreUpdate
   /* public void truncateTimestamps() {
        if (this.appointmentBookingTime != null)
            this.appointmentBookingTime = DateTimeUtils.truncateToSeconds(this.appointmentBookingTime);
        if (this.createdAt != null)
            this.createdAt = DateTimeUtils.truncateToSeconds(this.createdAt);
    }*/
}
