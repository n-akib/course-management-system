-- Creates the course_student join table for the many-to-many relationship between Course and Student entities.
-- This table links courses and students by storing their respective IDs.

CREATE SCHEMA IF NOT EXISTS cms;

CREATE TABLE cms.course_student (
    course_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    PRIMARY KEY (course_id, student_id),
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

-- Comments for clarity:
-- - course_id: References the ID of a course in the courses table.
-- - student_id: References the ID of a student in the students table.
-- - PRIMARY KEY: Ensures unique combinations of course_id and student_id.
-- - FOREIGN KEY constraints: Enforce referential integrity and cascade deletes to maintain data consistency.
