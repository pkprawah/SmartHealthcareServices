-- ===========================================
-- Flyway Migration: Recreate doctor_slots_availability table
-- Includes updated_at and version columns
-- ===========================================

DROP TABLE IF EXISTS doctor_slots_availability CASCADE;

CREATE TABLE doctor_slots_availability (
    doctor_slot_id BIGSERIAL PRIMARY KEY,

    doctor_id BIGINT NOT NULL,
    doctor_slot_day VARCHAR(50) NOT NULL,
    doctor_slot_start_time TIME NOT NULL,
    doctor_slot_end_time TIME NOT NULL,
    doctor_slot_status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,
    version INTEGER DEFAULT 0,

    CONSTRAINT fk_doctor_slot_doctor FOREIGN KEY (doctor_id)
        REFERENCES doctor(doctor_id)
);
