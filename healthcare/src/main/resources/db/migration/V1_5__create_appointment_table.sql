-- ===========================================
-- Table: appointment
-- Purpose: Stores appointments between patients and doctors
-- Depends on: doctor (doctor_id), patient (patient_id)
-- ===========================================

CREATE TABLE appointment (
    -- Auto-incremented primary key
    appointment_id BIGSERIAL PRIMARY KEY,

    -- Foreign key to doctor table
    doctor_id BIGINT NOT NULL,

    -- Foreign key to patient table
    patient_id BIGINT NOT NULL,

    -- The actual scheduled appointment date and time
    appointment_time TIMESTAMP,

    -- Record creation timestamp (populated on insert)
    created_at TIMESTAMP,

    -- Free text symptoms provided by patient
    symptoms TEXT,

    -- Enum stored as string (e.g., PENDING, CONFIRMED)
    status VARCHAR(50),

    -- Foreign key constraints
    CONSTRAINT fk_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id),
    CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES patient(patient_id)
);
