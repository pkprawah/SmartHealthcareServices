package com.teembinsys.core.healthcare.model;


import com.teembinsys.core.healthcare.constant.SlotStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "doctor_slots_availability")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSlotAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_availability_id", nullable = false, updatable = false)
    private Long doctorAvailabilityId;

    @Column(name = "doctor_availability_day", nullable = false)
    private String doctorAvailabilityDay;

    @Column(name = "doctor_slot_start_time", nullable = false)
    private LocalTime doctorSlotStartTime;

    @Column(name = "doctor_slot_end_time", nullable = false)
    private LocalTime doctorSlotEndTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "doctor_slot_status", nullable = false)
    private SlotStatus doctorSlotStatus = SlotStatus.AVAILABLE;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

   /* @Version
    @Column(name = "version")
    private Integer version;*/


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "max_bookings_per_day", nullable = false)
    private Integer maxBookingsPerDay;
}
