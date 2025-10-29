package com.teembinsys.core.healthcare.controller;


import com.teembinsys.core.healthcare.model.DoctorSlotAvailability;
import com.teembinsys.core.healthcare.request.DoctorSlotAvailabilityRequest;
import com.teembinsys.core.healthcare.response.DoctorSlotAvailabilityResponse;
import com.teembinsys.core.healthcare.services.DoctorSlotAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor-slots")
public class DoctorAvailabilitySlotController {

        @Autowired
        private DoctorSlotAvailabilityService slotService;

        @GetMapping
        public List<DoctorSlotAvailabilityResponse> getAllSlots() {
            return slotService.getAllSlots();
        }

        @GetMapping("/{id}")
        public ResponseEntity<DoctorSlotAvailabilityResponse> getSlotById(@PathVariable Integer id) {
            return slotService.getSlotById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

    @PostMapping
    public List<DoctorSlotAvailability> createMultipleSlots(@RequestBody List<DoctorSlotAvailabilityRequest> slotRequests) {
        return slotService.createMultipleSlots(slotRequests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorSlotAvailability> updateSlot(@PathVariable Integer id, @RequestBody DoctorSlotAvailabilityRequest request) {
        try {
            return ResponseEntity.ok(slotService.updateSlot(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteSlot(@PathVariable Integer id) {
            slotService.deleteSlot(id);
            return ResponseEntity.noContent().build();
        }


}
