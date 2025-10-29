package com.teembinsys.core.healthcare.repositories;


import com.teembinsys.core.healthcare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByPatientMobileNum(String mobileNumber);
    Optional<Patient> findByPatientId(Long patientId);

    Optional<Patient> findByCustomPatientId(String customPatientId);


}
