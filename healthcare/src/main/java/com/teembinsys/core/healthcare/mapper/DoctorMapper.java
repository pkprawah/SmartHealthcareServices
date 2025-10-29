package com.teembinsys.core.healthcare.mapper;

import com.teembinsys.core.healthcare.model.Doctor;
import com.teembinsys.core.healthcare.model.Specialization;
import com.teembinsys.core.healthcare.request.DoctorRequest;
import com.teembinsys.core.healthcare.request.SpecializationRequest;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorMapper {

    public static Doctor toEntity(DoctorRequest dto) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(dto.getDoctorId());
        doctor.setCustomDoctorId(dto.getCustomDoctorId());
        doctor.setDoctorName(dto.getName());
        doctor.setDoctorMobileNum(dto.getDoctorMobileNum());
        doctor.setDoctorAlternateMobileNum(dto.getDoctorMobileNum());

        // Base class fields
        doctor.setUserName(dto.getUserName());
        doctor.setPassword(dto.getPassword());
        doctor.setDateOfBirth(dto.getDateOfBirth());
        doctor.setAge(dto.getAge());
        doctor.setGender(dto.getGender());
        doctor.setEmail(dto.getEmailAddress());
        doctor.setAddress(dto.getAddress());
        doctor.setZipCode(dto.getZipCode());
        doctor.setDistrict(dto.getDistrict());
        doctor.setState(dto.getState());
        doctor.setCountry(dto.getCountry());
        doctor.setIdentification(dto.getIdentification());

        return doctor;
    }

    public static DoctorRequest toDTO(Doctor doctor) {
        DoctorRequest dto = new DoctorRequest();
        dto.setDoctorId(doctor.getDoctorId());
        dto.setCustomDoctorId(doctor.getCustomDoctorId());
        dto.setName(doctor.getDoctorName());

        // Base class fields
        dto.setUserName(doctor.getUserName());
        dto.setPassword(doctor.getPassword());
        dto.setDateOfBirth(doctor.getDateOfBirth());
        dto.setAge(doctor.getAge());
        dto.setGender(doctor.getGender());
        dto.setEmailAddress(doctor.getEmail());
        dto.setAddress(doctor.getAddress());
        dto.setZipCode(doctor.getZipCode());
        dto.setDistrict(doctor.getDistrict());
        dto.setState(doctor.getState());
        dto.setCountry(doctor.getCountry());
        dto.setIdentification(doctor.getIdentification());
        dto.setDoctorMobileNum(doctor.getDoctorMobileNum());
        dto.setDoctorAlternateMobileNum(doctor.getDoctorMobileNum());
        // Avoid recursion: map only specialization name or id
        List<SpecializationRequest> specDtos = doctor.getSpecializations().stream()
                .map(spec -> {
                    SpecializationRequest s = new SpecializationRequest();
                    s.setId(spec.getId());
                    s.setName(spec.getName());
                    return s;
                }).toList();
        dto.setDoctorSpecializations(specDtos);
        return dto;
    }

    public static void updateExistingDoctor(Doctor existing, DoctorRequest dto) {
        existing.setDoctorName(dto.getName());
        existing.setDoctorMobileNum(dto.getDoctorMobileNum());
        existing.setDoctorAlternateMobileNum(dto.getDoctorAlternateMobileNum());

        existing.setUserName(dto.getUserName());
        existing.setPassword(dto.getPassword());

        existing.setDateOfBirth(dto.getDateOfBirth());
        existing.setAge(dto.getAge());
        existing.setGender(dto.getGender());

        existing.setEmail(dto.getEmailAddress());
        existing.setAddress(dto.getAddress());
        existing.setZipCode(dto.getZipCode());
        existing.setDistrict(dto.getDistrict());
        existing.setState(dto.getState());
        existing.setCountry(dto.getCountry());

        // Only update identification if it’s different (you may validate uniqueness outside)
        if (dto.getIdentification() != null &&
                !dto.getIdentification().equals(existing.getIdentification())) {
            existing.setIdentification(dto.getIdentification());
        }
    }
}
