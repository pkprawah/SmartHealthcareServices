package com.teembinsys.core.healthcare.customexception;

public class SlotNotBelongsToDoctorException extends RuntimeException {
    public SlotNotBelongsToDoctorException(String message) {
        super(message);
    }
}
