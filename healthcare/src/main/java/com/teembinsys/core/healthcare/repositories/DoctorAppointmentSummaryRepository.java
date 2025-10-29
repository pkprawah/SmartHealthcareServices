package com.teembinsys.core.healthcare.repositories;

import com.teembinsys.core.healthcare.model.Doctor;
import com.teembinsys.core.healthcare.model.DoctorDailyAppointmentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface DoctorAppointmentSummaryRepository extends JpaRepository<DoctorDailyAppointmentSummary, Long> {

    Optional<DoctorDailyAppointmentSummary> findByDoctorAndStatsDate(Doctor doctor, LocalDateTime statsDate);

    @Modifying
    @Transactional
    @Query("""
        UPDATE DoctorAppointmentStats s
        SET s.totalBooked = s.totalBooked + 1,
            s.updatedAt = CURRENT_TIMESTAMP
        WHERE s.doctor = :doctor AND s.statsDate = :date
    """)
    int incrementBookedCount(Doctor doctor, LocalDate date);

    @Modifying
    @Transactional
    @Query("""
        UPDATE DoctorAppointmentStats s
        SET s.totalCancelled = s.totalCancelled + 1,
            s.updatedAt = CURRENT_TIMESTAMP
        WHERE s.doctor = :doctor AND s.statsDate = :date
    """)
    int incrementCancelledCount(Doctor doctor, LocalDate date);

    @Modifying
    @Transactional
    @Query("""
        UPDATE DoctorAppointmentStats s
        SET s.totalRescheduled = s.totalRescheduled + 1,
            s.updatedAt = CURRENT_TIMESTAMP
        WHERE s.doctor = :doctor AND s.statsDate = :date
    """)
    int incrementRescheduledCount(Doctor doctor, LocalDate date);

    @Modifying
    @Transactional
    @Query("""
        UPDATE DoctorAppointmentStats s
        SET s.totalNoShow = s.totalNoShow + 1,
            s.updatedAt = CURRENT_TIMESTAMP
        WHERE s.doctor = :doctor AND s.statsDate = :date
    """)
    int incrementNoShowCount(Doctor doctor, LocalDate date);
}
