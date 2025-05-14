package com.akib.courseManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course in the course management system.
 * This entity defines the structure and relationships for a course,
 * including its title, description, dates, instructor, and enrolled students.
 */
@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    /**
     * Unique identifier for the course, automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * The instructor assigned to teach the course.
     * Represents a many-to-one relationship with the Instructor entity.
     */
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    /**
     * List of students enrolled in the course.
     * Represents a many-to-many relationship with the Student entity,
     * mapped through the course_student join table.
     */
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();
}