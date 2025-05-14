-- V3__create_students_table.sql
-- Migration: Create the 'students' table
-- Author: Nagib Mahfuze Akib
-- Date: 2025-05-14

CREATE SCHEMA IF NOT EXISTS cms;

CREATE TABLE cms.students (
    id BIGSERIAL PRIMARY KEY,              -- Auto-incremented primary key
    name VARCHAR(255) NOT NULL,            -- Student's name
    email VARCHAR(255) NOT NULL UNIQUE     -- Student's email (unique constraint)
);
