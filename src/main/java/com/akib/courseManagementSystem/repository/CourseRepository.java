package com.akib.courseManagementSystem.repository;

import com.akib.courseManagementSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on Course entities.
 * Extends JpaRepository to provide standard database operations and custom queries.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * Retrieves a list of courses taught by a specific instructor.
     *
     * @return A list of Course entities associated with the specified instructor.
     */
    /** Updated findAll to exclude soft-deleted courses
     *
     */
    @Query("SELECT c FROM Course c WHERE c.deletedAt IS NULL")
    List<Course> findAll();

    /** Updated findById to exclude soft-deleted courses
     *
     * @param id
     * @return
     */
    @Query("SELECT c FROM Course c WHERE c.id = :id AND c.deletedAt IS NULL")
    Optional<Course> findById(Long id);

    /** Updated findByInstructorId to exclude soft-deleted courses
     *
     * @param instructorId
     * @return
     */
    @Query("SELECT c FROM Course c WHERE c.instructor.id = :instructorId AND c.deletedAt IS NULL")

    List<Course> findByInstructorId(Long instructorId);
}