package com.teembinsys.core.healthcare.services;

import com.teembinsys.core.healthcare.constant.AppointmentStatus;
import com.teembinsys.core.healthcare.constant.ErrorMessages;
import com.teembinsys.core.healthcare.constant.SlotStatus;
import com.teembinsys.core.healthcare.customexception.SlotNotBelongsToDoctorException;
import com.teembinsys.core.healthcare.mapper.AppointmentMapper;
import com.teembinsys.core.healthcare.model.DoctorSlotAvailability;
import com.teembinsys.core.healthcare.repositories.DoctorSlotAvailabilityRepository;
import com.teembinsys.core.healthcare.repositories.PatientRepository;
import com.teembinsys.core.healthcare.request.AppointmentRequest;
import com.teembinsys.core.healthcare.model.Appointment;
import com.teembinsys.core.healthcare.model.Doctor;
import com.teembinsys.core.healthcare.model.Patient;
import com.teembinsys.core.healthcare.repositories.AppointmentRepository;
import com.teembinsys.core.healthcare.repositories.DoctorRepository;
import com.teembinsys.core.healthcare.response.AppointmentResponse;
import com.teembinsys.core.healthcare.util.CommonUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;


@Service

public class AppointmentServices {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorSlotAvailabilityRepository doctorSlotAvailabilityRepository;

    @Autowired
    private  TokenGeneratorService tokenGeneratorService;

    @Autowired
    private DoctorDailyAppointmentSummaryService appointmentSummaryService;


   // @Transactional
    //public AppointmentResponse bookAppointment(AppointmentRequest request) {
        // ✅ Validate doctor
        ///Doctor doctor = doctorRepository.findById(request.getDoctorId())
            ///    .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        // ✅ Validate slot
       // DoctorSlotAvailability slot = doctorSlotAvailabilityRepository.findById(request.getDoctorSlotId())
        //        .orElseThrow(() -> new EntityNotFoundException("Slot not found"));

        // 🔒 Extra check: slot must belong to doctor
        //if (!slot.getDoctor().getDoctorId().equals(doctor.getDoctorId())) {
        //    throw new SlotNotBelongsToDoctorException(ErrorMessages.SLOT_DOESNOT_BELONGS_TO_DOCTOR);
        //}

       // if (slot.getDoctorSlotStatus() != SlotStatus.AVAILABLE) {
       //     throw new IllegalStateException("Doctor slot is not available for booking");
       // }

        // ✅ Find patient OR create new one
       // Patient patient = null;
        //if (request.getPatientId() != null) {
          //  patient = patientRepository.findById(request.getPatientId())
            //        .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + request.getPatientId()));
       // }
       /* else {
            patient = new Patient();
            patient.setPatientName(request.getPatientName());
            patient.setPatientMobileNum(request.getPatientMobile());
            patient.setAlternateMobileNum(request.getAlternateMobile());
            patient.setPatientRelation(request.getPatientRelation());
            patient = patientRepository.save(patient); // persist new patient
        }*/

        // ✅ Create appointment
       // Appointment appointment = new Appointment();
       // appointment.setDoctor(doctor);
       // appointment.setPatient(patient);
       // appointment.setAppointmentBookingTime(CommonUtils.truncateToSeconds(LocalDateTime.now())); // system time
       // appointment.setSymptoms(request.getSymptoms());
        //System.out.println(request + request.getSymptoms());
       // appointment.setStatus(AppointmentStatus.BOOKED);
       // appointment.setPatientSlotId(request.);

        //Appointment saved = appointmentRepository.save(appointment);

        // ✅ Update slot status
       // slot.setDoctorSlotStatus(SlotStatus.BOOKED);
        //doctorSlotAvailabilityRepository.save(slot);

        //return AppointmentMapper.toResponse(saved, slot);

    //}

   /* @Transactional
    public Appointment patchAppointment(AppointmentRequest request) {
        // 1. Fetch existing appointment
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        // 2. Handle slot update
        // 2. Handle slot update / reschedule
        if (request.getDoctorSlotId() != null) {
            // Load new slot
            DoctorAvailability newSlot = doctorSlotAvailabilityRepository.findById(request.getDoctorSlotId())
                    .orElseThrow(() -> new IllegalArgumentException("Slot not found"));

            // Ensure the new slot belongs to the same doctor as the appointment (doctorId must come from appointment)
            if (!newSlot.getDoctor().getDoctorId().equals(appointment.getDoctor().getDoctorId())) {
                throw new SlotNotBelongsToDoctorException(ErrorMessages.SLOT_DOESNOT_BELONGS_TO_DOCTOR);
            }

            // Ensure new slot is available
            if (newSlot.getDoctorSlotStatus() != SlotStatus.AVAILABLE) {
                throw new IllegalStateException("Slot is not available");
            }

            // If the appointment is already using the same slot id then nothing to do for slots
            Long currentSlotId = null;
            // try to derive current slot id from appointment entity (adjust to your model)
            if (appointment.getDoctorSlot() != null) {
                currentSlotId = appointment.getDoctorSlot().getId();
            } else if (appointment.getDoctorSlotId() != null) {
                currentSlotId = appointment.getDoctorSlotId();
            }

            if (!Objects.equals(currentSlotId, newSlot.getDoctorSlotId())) {
                // Release old slot -> set to AVAILABLE
                // Prefer to find the old slot by id if appointment stores it; otherwise, find by doctor + appointment time
                if (currentSlotId != null) {
                    doctorSlotAvailabilityRepository.findById(currentSlotId).ifPresent(oldSlot -> {
                        oldSlot.setDoctorSlotStatus(SlotStatus.AVAILABLE);
                        doctorSlotAvailabilityRepository.save(oldSlot);
                    });
                } else {
                    // fallback: try to find by doctor and appointment time (adjust repository method name)
                    doctorSlotAvailabilityRepository.findByDoctorAndStartDateTime(appointment.getDoctor(), appointment.getAppointmentTime())
                            .ifPresent(oldSlot -> {
                                oldSlot.setDoctorSlotStatus(SlotStatus.AVAILABLE);
                                doctorSlotAvailabilityRepository.save(oldSlot);
                            });
                }

                // Mark new slot as booked_rescheduled
                newSlot.setDoctorSlotStatus(SlotStatus.BOOKED_RESCHEDULED);
                doctorSlotAvailabilityRepository.save(newSlot);

                // Update appointment time to the new slot time and mark appointment as RESCHEDULED
                appointment.setAppointmentTime(newSlot.getStartDateTime()); // replace with actual getter
                // keep the same doctor from appointment (we validated above)
                // link appointment to the new slot (set object or id depending on your model)
                if (appointment.getDoctorSlot() != null) {
                    appointment.setDoctorSlot(newSlot);
                } else {
                    appointment.setDoctorSlotId(newSlot.getId());
                }
                appointment.setStatus(AppointmentStatus.RESCHEDULED);
            }
        }

// 3. Update symptoms
        if (request.getSymptoms() != null && !request.getSymptoms().isBlank()) {
            appointment.setSymptoms(request.getSymptoms());
        }

// 4. Update status (explicit status updates from client)
        if (request.getStatus() != null) {
            try {
                AppointmentStatus newStatus = AppointmentStatus.valueOf(request.getStatus().toUpperCase());
                appointment.setStatus(newStatus);
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException("Invalid status value: " + request.getStatus());
            }
        }

        return appointmentRepository.save(appointment);
    }*/


    /**
     * Book appointment with doctor, generate token, enforce daily limits
     */
    @Transactional
    public Appointment bookAppointment(Doctor doctor, Patient patient, LocalDateTime requestedTime) {
        String dayOfWeek = requestedTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();

        // 1️⃣ Fetch doctor's availability
        DoctorSlotAvailability availability = doctorSlotAvailabilityRepository
                .findByDoctorAndDayOfWeek(doctor, dayOfWeek)
                .orElseThrow(() -> new RuntimeException("Doctor not available on " + dayOfWeek));

        // 2️⃣ Validate requested time is within availability
        LocalTime start = availability.getDoctorSlotStartTime();
        LocalTime end = availability.getDoctorSlotEndTime();
        LocalTime request = requestedTime.toLocalTime();

        if (request.isBefore(start) || request.isAfter(end)) {
            throw new RuntimeException("Requested time outside doctor's working hours");
        }

        // 3️⃣ Check daily booking limit
        long bookedCount = appointmentRepository.countAppointmentsByDoctorAndDate(doctor, requestedTime.toLocalDate());
        if (bookedCount >= availability.getMaxBookingsPerDay()) {
            throw new RuntimeException("Doctor's booking limit reached for the day");
        }

        // 4️⃣ Generate token number (safe & global)
        LocalDate today = LocalDate.now();
        String nextSeq = tokenGeneratorService.generateNextTokenNumber(patient.getPatientMobileNum());

        String last4 = patient.getPatientMobileNum().substring(Math.max(patient.getPatientMobileNum().length() - 4, 0));
        String token = String.format("%s-%s-%s-%s",
                dayOfWeek.substring(0, 3).toUpperCase(),
                today,
                last4,
                nextSeq);

        Appointment appointment = new Appointment();
        String appointmentNumber = generateAppointmentNumber(patient.getPatientMobileNum(), appointment.getAppointmentId());
        appointment.setAppointmentNumber(appointmentNumber);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentBookingTime(CommonUtils.truncateToSeconds(LocalDateTime.now()));
        appointment.setPatientSequenceToken(token);
        String patientSlotTiming = availability.getDoctorSlotStartTime() + "-" + availability.getDoctorSlotEndTime();
        appointment.setPatientSlotId(patientSlotTiming);
        appointment.setDoctorSlotId(availability.getDoctorAvailabilityId());
        appointment.setStatus(AppointmentStatus.BOOKED);
        appointmentRepository.save(appointment);

        appointmentSummaryService.recordAppointmentBooked(appointment.getDoctor());

        // 6️⃣ Cleanup old token sequences
        //LocalDate cutoff = today.minusDays(7);
        //tokenSequenceRepository.deleteOldSequences(cutoff);

        return appointment;
    }

    /**
     * Generates a custom and traceable appointment number.
     *
     * Format: APT-<DATE><LAST4MOBILE>-<APPTID>
     *
     * Example: APT-PAWDR120251026-9210-15
     */
    private String generateAppointmentNumber(String mobileNumber, Long appointmentId) {
        final String PREFIX = "APT";

        LocalDateTime systemDateTime = CommonUtils.truncateToSeconds(LocalDateTime.now());
        String datePart = systemDateTime.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String mobilePart = (mobileNumber != null && mobileNumber.length() >= 4)
                ? mobileNumber.substring(mobileNumber.length() - 4)
                : "0000";
        // ✅ - Appointment ID part (for uniqueness)
        String appointmentIdPart = (appointmentId != null)
                ? String.format("%04d", appointmentId)
                : "0000";

        // ✅ - Combine with clear delimiters
        return String.format("%s-%s-%s-%s", PREFIX, datePart, mobilePart, appointmentIdPart);
    }

}
