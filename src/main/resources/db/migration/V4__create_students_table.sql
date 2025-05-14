-- V4__create_course_student_table.sql
-- Migration: Create the 'course_student' many-to-many relationship table
-- Author: Nagib Mahfuze Akib
-- Date: 2025-05-14

CREATE TABLE course_student (
    course_id BIGINT,                     -- Foreign key referencing the 'courses' table
    student_id BIGINT,                    -- Foreign key referencing the 'students' table
    PRIMARY KEY (course_id, student_id),  -- Composite primary key (course_id, student_id)
    FOREIGN KEY (course_id) REFERENCES courses(id),  -- Foreign key constraint on course_id
    FOREIGN KEY (student_id) REFERENCES students(id)  -- Foreign key constraint on student_id
);
