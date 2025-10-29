package com.teembinsys.core.healthcare.services;

import com.teembinsys.core.healthcare.model.Doctor;
import com.teembinsys.core.healthcare.model.DoctorDailyAppointmentSummary;
import com.teembinsys.core.healthcare.repositories.DoctorAppointmentSummaryRepository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

public class DoctorDailyAppointmentSummaryService {

    private DoctorAppointmentSummaryRepository doctorAppointmentSummaryRepository;

    @Transactional
    public void recordAppointmentBooked(Doctor doctor) {
        DoctorDailyAppointmentSummary stats = doctorAppointmentSummaryRepository
                .findByDoctorAndStatsDate(doctor, LocalDateTime.now())
                .orElseGet(() -> createNewStats(doctor));

        stats.setTotalBooked(stats.getTotalBooked() + 1);
        doctorAppointmentSummaryRepository.save(stats);
    }

    @Transactional
    public void recordAppointmentCancelled(Doctor doctor) {
        DoctorDailyAppointmentSummary stats = getOrCreate(doctor);
        stats.setTotalCancelled(stats.getTotalCancelled() + 1);
        doctorAppointmentSummaryRepository.save(stats);
    }

    @Transactional
    public void recordAppointmentRescheduled(Doctor doctor) {
        DoctorDailyAppointmentSummary stats = getOrCreate(doctor);
        stats.setTotalRescheduled(stats.getTotalRescheduled() + 1);
        doctorAppointmentSummaryRepository.save(stats);
    }

    @Transactional
    public void recordAppointmentNoShow(Doctor doctor) {
        DoctorDailyAppointmentSummary stats = getOrCreate(doctor);
        stats.setTotalNoShow(stats.getTotalNoShow() + 1);
        doctorAppointmentSummaryRepository.save(stats);
    }

    private DoctorDailyAppointmentSummary getOrCreate(Doctor doctor) {
        return doctorAppointmentSummaryRepository.findByDoctorAndStatsDate(doctor, LocalDateTime.now())
                .orElseGet(() -> createNewStats(doctor));
    }

    private DoctorDailyAppointmentSummary createNewStats(Doctor doctor) {
        DoctorDailyAppointmentSummary stats = new DoctorDailyAppointmentSummary();
        stats.setDoctor(doctor);
        stats.setSummaryDate(LocalDateTime.now());
        return doctorAppointmentSummaryRepository.save(stats);
    }
}
