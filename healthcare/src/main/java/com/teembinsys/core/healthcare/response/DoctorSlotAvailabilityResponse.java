package com.teembinsys.core.healthcare.response;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DoctorSlotAvailabilityResponse {

    private Integer doctorSlotId;
    private String doctorSlotDay;
    private String doctorSlotStartTime;
    private String doctorSlotEndTime;
    private String doctorSlotStatus;

    private String doctorName;
    private Long doctorId;
    private Set<String> specializations;
}
