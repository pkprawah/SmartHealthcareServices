-- ===========================================
-- Table: specialization
-- Purpose: Stores medical specializations (e.g., cardiology, dermatology)
-- Used in many-to-many mapping with doctor
-- ===========================================

CREATE TABLE specialization (
    -- Primary key
    specialization_id BIGSERIAL PRIMARY KEY,

    -- Name of the specialization (unique)
    specialization_name VARCHAR(255) NOT NULL UNIQUE
);
