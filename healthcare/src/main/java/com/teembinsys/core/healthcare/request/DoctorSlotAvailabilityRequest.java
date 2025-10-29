package com.teembinsys.core.healthcare.request;


import com.teembinsys.core.healthcare.constant.SlotStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class DoctorSlotAvailabilityRequest {
    private Long doctorId;
    private String doctorSlotDay;
    private LocalTime doctorSlotStartTime;
    private LocalTime doctorSlotEndTime;
    private SlotStatus doctorSlotStatus;
}
