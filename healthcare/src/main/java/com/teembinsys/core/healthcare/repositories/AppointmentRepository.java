package com.teembinsys.core.healthcare.repositories;


import com.teembinsys.core.healthcare.constant.AppointmentQueries;
import com.teembinsys.core.healthcare.model.Appointment;
import com.teembinsys.core.healthcare.model.Doctor;
import com.teembinsys.core.healthcare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(AppointmentQueries.COUNT_APPOINTMENTS_BY_DOCTOR_AND_DATE)
    long countAppointmentsByDoctorAndDate(
            @Param("doctor") Doctor doctor,
            @Param("appointmentDate") LocalDate appointmentDate
    );

    @Query(AppointmentQueries.FIND_UPCOMING_APPOINTMENTS)
    List<Appointment> findUpcomingAppointments(@Param("doctor") Doctor doctor);

    @Query(AppointmentQueries.EXISTS_SLOT_BOOKED)
    boolean isSlotAlreadyBooked(
            @Param("doctor") Doctor doctor,
            @Param("appointmentTime") java.time.LocalDateTime appointmentTime
    );

    @Query(AppointmentQueries.FIND_APPOINTMENTS_BY_PATIENT)
    List<Appointment> findByPatient(@Param("patient") Patient patient);
}
