package com.teembinsys.core.healthcare.repositories;

import com.teembinsys.core.healthcare.model.Doctor;
import com.teembinsys.core.healthcare.model.DoctorSlotAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface DoctorSlotAvailabilityRepository extends JpaRepository<DoctorSlotAvailability, Integer> {

    boolean existsByDoctorAndDoctorSlotDayAndDoctorSlotStartTime(
            Doctor doctor,
            String doctorSlotDay,
            LocalTime doctorSlotStartTime
    );

    Optional<DoctorSlotAvailability> findByDoctorAndDayOfWeek(Doctor doctor, String dayOfWeek);
}
