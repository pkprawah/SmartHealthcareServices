-- ===========================================
-- Table: doctor_slots_availability
-- Purpose: Defines time slot availability for doctors
-- Depends on: doctor (doctor_id)
-- ===========================================

CREATE TABLE doctor_slots_availability (
    -- Primary key
    doctor_slot_id BIGSERIAL PRIMARY KEY,

    -- Day of the week or date label for the slot
    doctor_slot_day VARCHAR(50) NOT NULL,

    -- Start time of the slot (e.g., 10:00 AM)
    doctor_slot_start_time TIME NOT NULL,

    -- End time of the slot (e.g., 11:00 AM)
    doctor_slot_end_time TIME NOT NULL,

    -- Enum status: AVAILABLE, BOOKED, UNAVAILABLE
    doctor_slot_status VARCHAR(20) NOT NULL,

    -- Timestamp when the slot was created
    created_at TIMESTAMP,

    -- Foreign key to the doctor table
    doctor_id BIGINT NOT NULL,

    CONSTRAINT fk_doctor_slot_doctor FOREIGN KEY (doctor_id)
        REFERENCES doctor(doctor_id)
);
