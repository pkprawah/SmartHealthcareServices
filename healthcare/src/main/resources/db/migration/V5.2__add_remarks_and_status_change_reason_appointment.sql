-- ===========================================
-- Script: Add remarks and status_change_reason columns to appointment table
-- Purpose: Adds missing columns safely (only if they don’t already exist)
-- ===========================================

DO $$
BEGIN
    -- Add remarks column if missing
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'appointment'
          AND column_name = 'remarks'
    ) THEN
        ALTER TABLE appointment
        ADD COLUMN remarks TEXT;
        RAISE NOTICE 'Column remarks added successfully to appointment table.';
    ELSE
        RAISE NOTICE 'Column remarks already exists. Skipping.';
    END IF;

    -- Add status_change_reason column if missing
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'appointment'
          AND column_name = 'status_change_reason'
    ) THEN
        ALTER TABLE appointment
        ADD COLUMN status_change_reason TEXT;
        RAISE NOTICE 'Column status_change_reason added successfully to appointment table.';
    ELSE
        RAISE NOTICE 'Column status_change_reason already exists. Skipping.';
    END IF;
END $$;
