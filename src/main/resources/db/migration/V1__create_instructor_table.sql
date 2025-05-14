-- V1__create_instructor_table.sql
-- Migration: Create the 'instructor' table
-- Author: Nagib Mahfuze Akib
-- Date: 2025-05-14

CREATE TABLE instructor (
    id BIGSERIAL PRIMARY KEY,             -- Auto-incremented primary key
    name VARCHAR(100) NOT NULL,           -- Instructor name
    email VARCHAR(100) UNIQUE NOT NULL    -- Unique email address
);
