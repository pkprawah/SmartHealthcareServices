package com.teembinsys.core.healthcare.constant;

public enum SlotStatus {

    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE"), // when doctor is not available for that particular day and time
    BOOKED("BOOKED"),

    BOOKED_RESCHEDULED("BOOKED_RESCHEDULED");


    private final String status;

    SlotStatus(String status) {
        this.status = status;
    }
}
