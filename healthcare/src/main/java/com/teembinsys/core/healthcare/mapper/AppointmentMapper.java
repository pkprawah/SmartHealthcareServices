package com.teembinsys.core.healthcare.mapper;

import com.teembinsys.core.healthcare.model.Appointment;
import com.teembinsys.core.healthcare.model.DoctorSlotAvailability;
import com.teembinsys.core.healthcare.response.AppointmentResponse;

public class AppointmentMapper {

    public static AppointmentResponse toResponse(Appointment appointment, DoctorSlotAvailability slot) {
        AppointmentResponse response = new AppointmentResponse();
        response.setAppointmentId(appointment.getAppointmentId());
        response.setDoctorName(appointment.getDoctor().getDoctorName());
        response.setPatientName(appointment.getPatient().getPatientName());
        response.setStatus(appointment.getStatus());
        response.setSlotDay(slot.getDoctorSlotDay());
        response.setSlotTime(slot.getDoctorSlotStartTime() + " - " + slot.getDoctorSlotEndTime());
        response.setSymptoms(appointment.getSymptoms());
        return response;
    }
}
