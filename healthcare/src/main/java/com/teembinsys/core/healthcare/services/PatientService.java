package com.teembinsys.core.healthcare.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.teembinsys.core.healthcare.mapper.PatientMapper;
import com.teembinsys.core.healthcare.request.PatientRequest;
import com.teembinsys.core.healthcare.model.Patient;
import com.teembinsys.core.healthcare.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientRequest createPatient(PatientRequest dto) {
        Patient saved = patientRepository.save(PatientMapper.convertToEntity(dto));
        return PatientMapper.convertToDTO(saved);
    }

    public List<PatientRequest> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(PatientMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PatientRequest> getPatientById(Long id) {
        return patientRepository.findById(id).map(PatientMapper::convertToDTO);
    }

    public PatientRequest updatePatient(Long id, PatientRequest updatedDto) {
        return patientRepository.findById(id).map(existing -> {
            Patient updated = PatientMapper.convertToEntity(updatedDto);
            updated.setPatientId(existing.getPatientId()); // preserve ID
            return PatientMapper.convertToDTO(patientRepository.save(updated));
        }).orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
