package com.teembinsys.core.healthcare.services;


import com.teembinsys.core.healthcare.mapper.DoctorSlotAvailabilityMapper;
import com.teembinsys.core.healthcare.mapper.DoctorSlotAvailabilityResponseMapper;
import com.teembinsys.core.healthcare.model.Doctor;
import com.teembinsys.core.healthcare.model.DoctorSlotAvailability;
import com.teembinsys.core.healthcare.repositories.DoctorRepository;
import com.teembinsys.core.healthcare.repositories.DoctorSlotAvailabilityRepository;
import com.teembinsys.core.healthcare.request.DoctorSlotAvailabilityRequest;
import com.teembinsys.core.healthcare.response.DoctorSlotAvailabilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorSlotAvailabilityService {
    @Autowired
    private DoctorSlotAvailabilityRepository doctorSlotAvailabilityRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    public List<DoctorSlotAvailabilityResponse> getAllSlots() {

        return doctorSlotAvailabilityRepository.findAll()
                .stream()
                    .map(DoctorSlotAvailabilityResponseMapper::toResponse)
                        .collect(Collectors.toList());


    }

    public Optional<DoctorSlotAvailabilityResponse> getSlotById(Integer id) {
        return doctorSlotAvailabilityRepository.findById(id)
                .stream()
                .map(DoctorSlotAvailabilityResponseMapper::toResponse)
                .toList().stream().findAny();
    }

    public List<DoctorSlotAvailability> createMultipleSlots(List<DoctorSlotAvailabilityRequest> requests) {
        List<DoctorSlotAvailability> slotEntities = requests.stream()
                .map(request -> {
                    Doctor doctor = doctorRepository.findById(request.getDoctorId())
                            .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + request.getDoctorId()));

                    boolean isDoctorAndDoctorSlotDayAndDoctorSlotStartTimeExists  =doctorSlotAvailabilityRepository.existsByDoctorAndDoctorSlotDayAndDoctorSlotStartTime(
                          doctor, request.getDoctorSlotDay(), request.getDoctorSlotStartTime());
                    if (isDoctorAndDoctorSlotDayAndDoctorSlotStartTimeExists) {
                        throw new RuntimeException("Slot already exists for doctor ID: " + request.getDoctorId()
                                + " on " + request.getDoctorSlotDay()
                                + " at " + request.getDoctorSlotStartTime());
                    }


                    return DoctorSlotAvailabilityMapper.toEntity(request, doctor);
                })
                .collect(Collectors.toList());

        return doctorSlotAvailabilityRepository.saveAll(slotEntities);
    }


    public DoctorSlotAvailability updateSlot(Integer id, DoctorSlotAvailabilityRequest request) {
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return doctorSlotAvailabilityRepository.findById(id)
                .map(existing -> {
                    DoctorSlotAvailabilityMapper.updateEntity(existing, request, doctor);
                    try {
                        return doctorSlotAvailabilityRepository.save(existing);
                    } catch (ObjectOptimisticLockingFailureException e) {
                        throw new RuntimeException("Slot is already booked or modified. Please refresh and try again.");
                    }
                }).orElseThrow(() -> new RuntimeException("Slot not found"));

    }
    public void deleteSlot(Integer id) {
        doctorSlotAvailabilityRepository.deleteById(id);
    }
}
