package com.akib.courseManagementSystem.repository;

import com.akib.courseManagementSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on Course entities.
 * Extends JpaRepository to provide standard database operations and custom queries.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * Retrieves a list of courses taught by a specific instructor.
     *
     * @param instructorId The ID of the instructor.
     * @return A list of Course entities associated with the specified instructor.
     */
    List<Course> findByInstructorId(Long instructorId);
}