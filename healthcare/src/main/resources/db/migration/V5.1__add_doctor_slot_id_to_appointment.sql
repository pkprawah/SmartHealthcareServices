-- ===========================================
-- Script: Add patient_slot_id column to appointment table
-- Purpose: Always adds patient_slot_id if it doesn't exist
-- ===========================================

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'appointment'
          AND column_name = 'patient_slot_id'
    ) THEN
        ALTER TABLE appointment
        ADD COLUMN patient_slot_id BIGINT;

        RAISE NOTICE 'Column patient_slot_id added successfully to appointment table.';
    ELSE
        RAISE NOTICE 'Column patient_slot_id already exists. Skipping.';
    END IF;
END $$;

-- ===========================================
-- Optional: Add foreign key constraint (example)
-- ===========================================

-- ALTER TABLE appointment
-- ADD CONSTRAINT fk_appointment_patient_slot
-- FOREIGN KEY (patient_slot_id)
-- REFERENCES patient_slot_availability(patient_slot_id);
