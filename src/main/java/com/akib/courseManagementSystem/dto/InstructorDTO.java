package com.akib.courseManagementSystem.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing an instructor in the course management system.
 * Used to transfer instructor data between layers, including their identifier, name, and email.
 */
@Data
public class InstructorDTO {
    /**
     * Unique identifier for the instructor.
     */
    private Long id;

    /**
     * The full name of the instructor.
     */
    private String name;

    /**
     * The email address of the instructor.
     */
    private String email;
}