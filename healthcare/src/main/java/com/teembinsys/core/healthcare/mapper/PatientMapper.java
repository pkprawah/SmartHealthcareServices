package com.teembinsys.core.healthcare.mapper;

import com.teembinsys.core.healthcare.model.Patient;
import com.teembinsys.core.healthcare.request.PatientRequest;

public class PatientMapper {

    public static Patient convertToEntity(PatientRequest dto) {
        Patient patient = new Patient();
        patient.setPatientId(dto.getPatientId());
        patient.setCustomPatientId(dto.getCustomPatientId());
        patient.setPatientName(dto.getPatientName());
        patient.setPatientMobileNum(dto.getPatientMobileNum());
        patient.setAlternateMobileNum(dto.getAlternateMobileNum());
        patient.setPatientRelation(dto.getPatientRelation());

        // Base class fields
        patient.setUserName(dto.getUserName());
        patient.setPassword(dto.getPassword());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setEmail(dto.getEmailAddress());
        patient.setAddress(dto.getAddress());
        patient.setZipCode(dto.getZipCode());
        patient.setDistrict(dto.getDistrict());
        patient.setState(dto.getState());
        patient.setCountry(dto.getCountry());
        patient.setIdentification(dto.getIdentification());
        //patient.setUserType(dto.getUserType());

        return patient;
    }

    public static PatientRequest convertToDTO(Patient patient) {
        PatientRequest request = new PatientRequest();
        request.setPatientId(patient.getPatientId());
        request.setCustomPatientId(patient.getCustomPatientId());
        request.setPatientName(patient.getPatientName());
        request.setPatientMobileNum(patient.getPatientMobileNum());
        request.setAlternateMobileNum(patient.getAlternateMobileNum());
        request.setPatientRelation(patient.getPatientRelation());

        // Base class fields
        request.setUserName(patient.getUserName());
        request.setPassword(patient.getPassword());
        request.setDateOfBirth(patient.getDateOfBirth());
        request.setAge(patient.getAge());
        request.setGender(patient.getGender());
        request.setEmailAddress(patient.getEmail());
        request.setAddress(patient.getAddress());
        request.setZipCode(patient.getZipCode());
        request.setDistrict(patient.getDistrict());
        request.setState(patient.getState());
        request.setCountry(patient.getCountry());
        request.setIdentification(patient.getIdentification());
        //request.setUserType(patient.getUserType());

        return request;
    }

}
