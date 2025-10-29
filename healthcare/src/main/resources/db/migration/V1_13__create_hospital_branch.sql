CREATE TABLE hospital_branch (
    branch_id BIGSERIAL PRIMARY KEY,

    branch_code VARCHAR(50) UNIQUE NOT NULL,
    branch_name VARCHAR(100) NOT NULL,
    address TEXT,
    phone_number VARCHAR(20),

    -- Self-referencing column for main branch
    parent_branch_id BIGINT NULL,

    -- Indicates whether this is a MAIN or SUB branch
    branch_type VARCHAR(20) DEFAULT 'MAIN' CHECK (branch_type IN ('MAIN', 'SUB')),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_parent_branch
        FOREIGN KEY (parent_branch_id)
        REFERENCES hospital_branch(branch_id)
        ON DELETE SET NULL
);
