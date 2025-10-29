-- V1_9__create_token_sequence_table.sql
-- Create token_sequence table for tracking global token increments

CREATE TABLE IF NOT EXISTS token_sequence (
    id BIGSERIAL PRIMARY KEY,
    last_value BIGINT NOT NULL DEFAULT 0,
    last_reset_time TIMESTAMP
);
