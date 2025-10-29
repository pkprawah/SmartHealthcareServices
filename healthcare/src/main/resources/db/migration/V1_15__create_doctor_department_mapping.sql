CREATE TABLE doctor_department_mapping (
    mapping_id BIGSERIAL PRIMARY KEY,

    doctor_id BIGINT NOT NULL,
    branch_id BIGINT NOT NULL,
    department_id BIGINT NOT NULL,
    specialization_id BIGINT NOT NULL,

    consultation_fee DECIMAL(10,2) NOT NULL,
    availability_status VARCHAR(20) DEFAULT 'AVAILABLE',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_ddm_doctor FOREIGN KEY (doctor_id)
        REFERENCES doctor(doctor_id) ON DELETE CASCADE,

    CONSTRAINT fk_ddm_branch FOREIGN KEY (branch_id)
        REFERENCES hospital_branch(branch_id) ON DELETE CASCADE,

    CONSTRAINT fk_ddm_department FOREIGN KEY (department_id)
        REFERENCES department(department_id) ON DELETE CASCADE,

    CONSTRAINT fk_ddm_specialization FOREIGN KEY (specialization_id)
        REFERENCES specialization(specialization_id) ON DELETE CASCADE,

    CONSTRAINT uq_doctor_dept_spec_branch UNIQUE (doctor_id, branch_id, department_id, specialization_id)
);
