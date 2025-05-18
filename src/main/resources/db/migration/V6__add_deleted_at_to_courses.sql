-- Add deleted_at column to courses table for soft delete
ALTER TABLE courses
ADD COLUMN deleted_at TIMESTAMP;