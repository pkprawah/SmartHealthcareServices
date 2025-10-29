-- ===========================================
-- Table: patient
-- Purpose: Stores patient profile and contact information
-- Inherits common fields from Base class
-- ===========================================

CREATE TABLE patient (
    -- Primary key
    patient_id BIGSERIAL PRIMARY KEY,

    -- Custom patient identifier like PAT-RAJ123
    custom_patient_id VARCHAR(255) UNIQUE,

    -- Patient's name
    patient_name VARCHAR(255) UNIQUE,

    user_name VARCHAR(30) NOT NULL,

    password VARCHAR(30) NOT NULL,

    -- Primary mobile number
    patient_mobile VARCHAR(15) UNIQUE,

    -- Optional alternate mobile number
    patient_alternate_mobile VARCHAR(15) UNIQUE,

    email VARCHAR(30),

    -- Relation to main account (for family booking)
    patient_relation VARCHAR(255) UNIQUE,

    -- Base fields

    date_of_birth DATE NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address VARCHAR(100),
    zip_code INT,
    district VARCHAR(30),
    state VARCHAR(30),
    country VARCHAR(20),
    identification VARCHAR(64) UNIQUE
);
