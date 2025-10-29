-- ===========================================
-- Table: doctor_specializations
-- Purpose: Join table for many-to-many relationship
-- between doctors and their specializations
-- Depends on: doctor(doctor_id), specialization(specialization_id)
-- ===========================================

CREATE TABLE doctor_specializations (
    -- FK to doctor table
    doctor_id BIGINT NOT NULL,

    -- FK to specialization table
    specialization_id BIGINT NOT NULL,

    -- Composite primary key
    PRIMARY KEY (doctor_id, specialization_id),

    -- Foreign key constraints
    CONSTRAINT fk_doctor_specializations_doctor
        FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id),

    CONSTRAINT fk_doctor_specializations_specialization
        FOREIGN KEY (specialization_id) REFERENCES specialization(specialization_id)
);
