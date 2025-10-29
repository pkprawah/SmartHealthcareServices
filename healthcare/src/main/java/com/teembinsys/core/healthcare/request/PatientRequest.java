package com.teembinsys.core.healthcare.request;

import com.teembinsys.core.healthcare.constant.UserTypes;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;


@Data
public class PatientRequest {

    private Long patientId;
    private String customPatientId;
    private String patientName;
    private String patientMobileNum;
    private String alternateMobileNum;
    private String patientRelation;

    // From Base class
    private String userName;
    private String password;
    private LocalDate dateOfBirth;
    private Integer age;
    private String gender;
    private String emailAddress;
    private String address;
    private Integer zipCode;
    private String district;
    private String state;
    private String country;
    private String identification;
    private UserTypes userType = UserTypes.PATIENT;
}
