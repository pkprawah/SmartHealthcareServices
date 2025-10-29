-- ===========================================
-- Table: doctor
-- Purpose: Stores doctor information and contact details
-- Inherits user fields from Base class
-- ===========================================

CREATE TABLE doctor (
    doctor_id BIGSERIAL PRIMARY KEY,
    doctor_code VARCHAR(255),
    doctor_name VARCHAR(255),
    user_name VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    doctor_mobile VARCHAR(15) UNIQUE,
    doctor_alternate_mobile VARCHAR(15),
    date_of_birth DATE NOT NULL,
    qualification VARCHAR(255),
    experience_years INT,
    registration_number VARCHAR(100),
    joining_date DATE,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    email VARCHAR(30),
    photo_url VARCHAR(255),
    address TEXT,
    zip_code INT,
    district VARCHAR(30),
    state VARCHAR(30),
    country VARCHAR(20),
    identification VARCHAR(64) UNIQUE
    created_at TIMESTAMP
    updated_at TIMESTAMP
);
