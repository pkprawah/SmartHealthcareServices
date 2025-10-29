package com.teembinsys.core.healthcare.constant;

public enum DoctorStatus {

    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE"),
    BOOKED_WITH_PATIENT("BOOKED_WITH_PATIENT");

    private final String status;

    DoctorStatus(String status) {
        this.status = status;
    }
}
