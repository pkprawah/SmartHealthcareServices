package com.teembinsys.core.healthcare.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teembinsys.core.healthcare.constant.AppointmentStatus;
import com.teembinsys.core.healthcare.model.Doctor;
import com.teembinsys.core.healthcare.model.Patient;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Setter
public class AppointmentRequest {
    private Long appointmentId;
    private Long doctorId;
    private Long patientId;
    private Integer doctorSlotId;
    private LocalDateTime appointmentBookingTime;
    private String symptoms;
    private String status;
    private String remarks;
    private String statusChangeReason;
    private Integer patientSlotId;

}
