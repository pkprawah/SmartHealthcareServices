package com.teembinsys.core.healthcare.response;


import com.teembinsys.core.healthcare.constant.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponse {

    private Long appointmentId;
    private String doctorName;
    private String patientName;
    private AppointmentStatus status;
    private String slotDay;
    private String slotTime;
    private String symptoms;
}
