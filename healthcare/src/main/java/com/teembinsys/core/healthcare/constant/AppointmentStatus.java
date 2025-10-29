package com.teembinsys.core.healthcare.constant;


import lombok.Getter;

@Getter
public enum AppointmentStatus {
    BOOKED("BOOKED"),
    REOPEN("REOPEN"),
    SCHEDULED("SCHEDULED"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED"),
    NOSHOW("NOSHOW"),
    RESCHEDULED("RESCHEDULED");

    private final String status;

    AppointmentStatus(String status) {
        this.status = status;
    }
}
