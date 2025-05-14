package com.akib.courseManagementSystem.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing a student in the course management system.
 * Used to transfer student data between layers, including their identifier, name, and email.
 */
@Data
public class StudentDTO {
    /**
     * Unique identifier for the student.
     */
    private Long id;

    /**
     * The full name of the student.
     */
    private String name;

    /**
     * The email address of the student.
     */
    private String email;
}