package com.teembinsys.core.healthcare.mapper;

import com.teembinsys.core.healthcare.model.Doctor;
import com.teembinsys.core.healthcare.model.DoctorSlotAvailability;
import com.teembinsys.core.healthcare.request.DoctorSlotAvailabilityRequest;

public class DoctorSlotAvailabilityMapper {
    /**
     * Converts a DoctorSlotAvailabilityRequest to a DoctorSlotAvailability entity.
     * This method is typically used for creating a new slot.
     */
    public static DoctorSlotAvailability toEntity(DoctorSlotAvailabilityRequest request, Doctor doctor) {
        DoctorSlotAvailability slot = new DoctorSlotAvailability();
        slot.setDoctor(doctor);
        //slot.setDoctorSlotDay(request.getDoctorSlotDay());
        slot.setDoctorSlotStartTime(request.getDoctorSlotStartTime());
        slot.setDoctorSlotEndTime(request.getDoctorSlotEndTime());
        slot.setDoctorSlotStatus(request.getDoctorSlotStatus());
        return slot;
    }

    /**
     * Updates an existing DoctorSlotAvailability entity with values from the request.
     * This method is typically used for update operations.
     */
    public static void updateEntity(DoctorSlotAvailability existing, DoctorSlotAvailabilityRequest request, Doctor doctor) {
        existing.setDoctor(doctor);
        existing.setDoctorSlotDay(request.getDoctorSlotDay());
        existing.setDoctorSlotStartTime(request.getDoctorSlotStartTime());
        existing.setDoctorSlotEndTime(request.getDoctorSlotEndTime());
        existing.setDoctorSlotStatus(request.getDoctorSlotStatus());
    }
}
