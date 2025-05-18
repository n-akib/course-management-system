package com.akib.courseManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student in the course management system.
 * This entity defines the structure and relationships for a student,
 * including their name, email, and the courses they are enrolled in.
 */
@Entity
@Table(name = "students", schema = "cms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    /**
     * Unique identifier for the student, automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The full name of the student.
     */
    private String name;

    /**
     * The email address of the student, must be unique and non-null.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /** Added deleted_at column for soft delete
     *
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    /**
     * List of courses the student is enrolled in.
     * Represents a many-to-many relationship with the Course entity,
     * mapped through the course_student join table in the Course entity.
     */
    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();
}