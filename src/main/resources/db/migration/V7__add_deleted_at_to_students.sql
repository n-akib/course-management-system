-- Add deleted_at column to students table for soft delete
ALTER TABLE students
ADD COLUMN deleted_at TIMESTAMP;