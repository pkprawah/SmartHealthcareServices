package com.teembinsys.core.healthcare.constant;

public final class AppointmentQueries {

    private AppointmentQueries() {} // prevent instantiation

    // 🔹 Query to count daily appointments for a doctor
    public static final String COUNT_APPOINTMENTS_BY_DOCTOR_AND_DATE =
            "SELECT COUNT(a) FROM Appointment a " +
                    "WHERE a.doctor = :doctor AND DATE(a.appointmentTime) = :appointmentDate";

    // 🔹 Query to fetch appointments by doctor and day
    public static final String FIND_APPOINTMENTS_BY_DOCTOR_AND_DAY =
            "SELECT a FROM Appointment a " +
                    "WHERE a.doctor = :doctor AND FUNCTION('DATE_PART', 'dow', a.appointmentTime) = :dayOfWeek";

    // 🔹 Query to fetch upcoming appointments
    public static final String FIND_UPCOMING_APPOINTMENTS =
            "SELECT a FROM Appointment a " +
                    "WHERE a.doctor = :doctor AND a.appointmentTime >= CURRENT_TIMESTAMP " +
                    "ORDER BY a.appointmentTime ASC";

    // 🔹 Query to check if slot already booked
    public static final String EXISTS_SLOT_BOOKED =
            "SELECT COUNT(a) > 0 FROM Appointment a " +
                    "WHERE a.doctor = :doctor AND a.appointmentTime = :appointmentTime";

    // 🔹 Query to find all by patient
    public static final String FIND_APPOINTMENTS_BY_PATIENT =
            "SELECT a FROM Appointment a WHERE a.patient = :patient ORDER BY a.appointmentTime DESC";
}

