DROP TABLE IF EXISTS doctor_slots_availability CASCADE;

CREATE TABLE doctor_slots_availability (
    doctor_slot_id BIGSERIAL PRIMARY KEY,
    doctor_id BIGINT NOT NULL,
    doctor_slot_day VARCHAR(50) NOT NULL,
    doctor_slot_start_time TIME NOT NULL,
    doctor_slot_end_time TIME NOT NULL,
    doctor_slot_status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,  -- moved or added where you want
    CONSTRAINT fk_doctor_slot_doctor FOREIGN KEY (doctor_id)
        REFERENCES doctor(doctor_id)
);
