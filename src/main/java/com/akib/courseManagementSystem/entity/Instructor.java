package com.akib.courseManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an instructor in the course management system.
 * This entity defines the structure and relationships for an instructor,
 * including their name, email, and the courses they teach.
 */
@Entity
@Table(name = "instructor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructor {
    /**
     * Unique identifier for the instructor, automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The full name of the instructor.
     */
    private String name;

    /**
     * The email address of the instructor, must be unique and non-null.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * List of courses taught by the instructor.
     * Represents a one-to-many relationship with the Course entity,
     * where the instructor is referenced in the Course entity.
     * Cascades all operations and uses lazy fetching for performance.
     */
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();
}