CREATE TABLE doctor_daily_appointment_summary (
    id BIGSERIAL PRIMARY KEY,
    doctor_id BIGINT NOT NULL,
    summary_date DATE NOT NULL DEFAULT CURRENT_DATE,
    total_booked INT DEFAULT 0,
    total_cancelled INT DEFAULT 0,
    total_rescheduled INT DEFAULT 0,
    total_no_show INT DEFAULT 0,
    total_reopen INT DEFAULT 0,
    total_completed INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_summary_doctor FOREIGN KEY (doctor_id)
        REFERENCES doctor(doctor_id),
    CONSTRAINT uc_doctor_date UNIQUE (doctor_id, summary_date)
);
