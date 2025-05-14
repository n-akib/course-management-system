-- V2__create_courses_table.sql
-- Migration: Create the 'courses' table
-- Author: Nagib Mahfuze Akib
-- Date: 2025-05-14

CREATE SCHEMA IF NOT EXISTS cms;

CREATE TABLE cms.courses (
    id BIGSERIAL PRIMARY KEY,                           -- Auto-incremented primary key
    title VARCHAR(255) NOT NULL,                        -- Course title
    description TEXT,                                   -- Course description
    start_date DATE,                                    -- Course start date
    end_date DATE,                                      -- Course end date
    instructor_id BIGINT,                               -- FK to instructor
    FOREIGN KEY (instructor_id) REFERENCES instructor(id)
);
