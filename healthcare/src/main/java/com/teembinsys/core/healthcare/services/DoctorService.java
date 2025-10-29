package com.teembinsys.core.healthcare.services;

import com.teembinsys.core.healthcare.mapper.DoctorMapper;
import com.teembinsys.core.healthcare.model.Doctor;
import com.teembinsys.core.healthcare.model.Specialization;
import com.teembinsys.core.healthcare.repositories.DoctorRepository;
import com.teembinsys.core.healthcare.repositories.SpecializationRepository;
import com.teembinsys.core.healthcare.request.DoctorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    public DoctorRequest createDoctor(DoctorRequest doctorRequest) {
        Set<Specialization> specializations = Optional.ofNullable(doctorRequest.getSpecializations())
                .orElse(Collections.emptySet())
                .stream()
                .map(name -> specializationRepository.findByName(name)
                        .orElseGet(() -> specializationRepository.save(new Specialization(name))))
                .collect(Collectors.toSet());

        Doctor doctor = DoctorMapper.toEntity(doctorRequest);

        doctor.setSpecializations(specializations);
        String mobileSuffix = doctorRequest.getDoctorMobileNum();
        if (mobileSuffix != null && mobileSuffix.length() >= 4) {
            mobileSuffix = mobileSuffix.substring(mobileSuffix.length() - 4); // last 6 digits
        } else {
            mobileSuffix = "000000";
        }
        Doctor saved = doctorRepository.save(doctor);

        String namePrefix = saved.getDoctorName().length() >= 3
                ? saved.getDoctorName().substring(0, 3).toUpperCase()
                : saved.getDoctorName().toUpperCase();
        saved.setCustomDoctorId("DOC-" + namePrefix+mobileSuffix+saved.getDoctorId());
        doctorRepository.save(saved);
        return DoctorMapper.toDTO(saved);
    }

    public List<DoctorRequest> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(DoctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<DoctorRequest> getDoctorById(Long id) {
        return doctorRepository.findById(id).map(DoctorMapper::toDTO);
    }

    public DoctorRequest updateDoctor(Long id, DoctorRequest updatedDto) {
        return doctorRepository.findById(id).map(existing -> {

            // Check only if identification has changed
            String newIdentification = updatedDto.getIdentification();
            String currentIdentification = existing.getIdentification();

            if (newIdentification != null &&
                    !newIdentification.equals(currentIdentification) &&
                    doctorRepository.existsByIdentificationAndDoctorIdNot(newIdentification, existing.getDoctorId())) {
                throw new IllegalArgumentException("Identification already exists: " + newIdentification);
            }


            DoctorMapper.updateExistingDoctor(existing, updatedDto);
            return DoctorMapper.toDTO(doctorRepository.save(existing));

        }).orElseThrow(() -> new RuntimeException("Doctor not found with id " + id));
    }



    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
