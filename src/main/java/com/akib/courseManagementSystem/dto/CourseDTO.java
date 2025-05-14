package com.akib.courseManagementSystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a course in the course management system.
 * Used to transfer course data between layers, including course details, instructor, and enrolled students.
 */
@Data
public class CourseDTO {
    /**
     * Unique identifier for the course.
     */
    private Long id;

    /**
     * The title of the course.
     */
    private String title;

    /**
     * A brief description of the course content.
     */
    private String description;

    /**
     * The start date of the course.
     */
    private LocalDate startDate;

    /**
     * The end date of the course.
     */
    private LocalDate endDate;

    /**
     * The identifier of the instructor assigned to the course.
     */
    private Long instructorId;

    /**
     * List of identifiers for students enrolled in the course.
     */
    private List<Long> studentIds;
}