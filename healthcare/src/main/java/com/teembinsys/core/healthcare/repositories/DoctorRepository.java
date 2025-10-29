package com.teembinsys.core.healthcare.repositories;


import com.teembinsys.core.healthcare.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByCustomDoctorId(String customDoctorId); // <-- Add this

    Optional<Doctor> findByDoctorId(Long doctorId);

    boolean existsByIdentificationAndDoctorIdNot(String identification, Long doctorId);


}
