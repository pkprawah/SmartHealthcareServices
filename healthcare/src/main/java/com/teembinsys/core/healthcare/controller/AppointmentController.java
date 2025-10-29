package com.teembinsys.core.healthcare.controller;

import com.teembinsys.core.healthcare.request.AppointmentRequest;
import com.teembinsys.core.healthcare.response.AppointmentResponse;
import com.teembinsys.core.healthcare.services.AppointmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentServices appointmentService;

    @PostMapping("/booking")
    public ResponseEntity<AppointmentResponse
            > bookAppointment(@RequestBody AppointmentRequest request) {
        AppointmentResponse response = appointmentService.bookAppointment(request);
        return ResponseEntity.ok(response);
    }

    /*@PatchMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponse> patchAppointment(
            @PathVariable Long appointmentId,
            @RequestBody AppointmentRequest request) {

        request.setAppointmentId(appointmentId);
        Appointment updated = appointmentService.patchAppointment(request);
        return ResponseEntity.ok(AppointmentMapper.toResponse(updated));
    }*/

}
