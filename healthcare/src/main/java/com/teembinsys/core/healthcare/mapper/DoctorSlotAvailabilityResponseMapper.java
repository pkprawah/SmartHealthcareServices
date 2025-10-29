package com.teembinsys.core.healthcare.mapper;

import com.teembinsys.core.healthcare.model.DoctorSlotAvailability;
import com.teembinsys.core.healthcare.model.Specialization;
import com.teembinsys.core.healthcare.response.DoctorSlotAvailabilityResponse;

import java.util.stream.Collectors;

public class DoctorSlotAvailabilityResponseMapper {

    public static DoctorSlotAvailabilityResponse toResponse(DoctorSlotAvailability slot) {
        DoctorSlotAvailabilityResponse response = new DoctorSlotAvailabilityResponse();

       // response.setDoctorSlotId(slot.getDoctorSlotId());
       // response.setDoctorSlotDay(slot.getDoctorSlotDay());
        response.setDoctorSlotStartTime(slot.getDoctorSlotStartTime().toString());
        response.setDoctorSlotEndTime(slot.getDoctorSlotEndTime().toString());
        response.setDoctorSlotStatus(slot.getDoctorSlotStatus().name());

        if (slot.getDoctor() != null) {
            response.setDoctorName(slot.getDoctor().getDoctorName());
            response.setSpecializations(
                    slot.getDoctor().getSpecializations()
                            .stream()
                            .map(Specialization::getName)
                            .collect(Collectors.toSet())
            );
        }

        return response;
    }
}
