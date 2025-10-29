package com.teembinsys.core.healthcare.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Setter
@Getter
public class DoctorRequest {

    private Long doctorId;
    private String customDoctorId;
    private String name;
    private Set<String> specializations;
    private Set<Long> specializationIds; // or use names if preferred
    // Inherited fields from Base class
    private String userName;
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
    private String userType;
    private String doctorMobileNum;
    private String doctorAlternateMobileNum;

    // Updated to avoid recursion
    private List<SpecializationRequest> doctorSpecializations;

    // Optional: still keep if you use IDs to associate
    private List<Long> doctorSpecializationIds;

}
