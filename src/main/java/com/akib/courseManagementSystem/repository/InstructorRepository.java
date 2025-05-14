package com.akib.courseManagementSystem.repository;

import com.akib.courseManagementSystem.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Instructor entities.
 * Extends JpaRepository to provide standard database operations for Instructor entities.
 */
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    // No custom query methods defined; standard JpaRepository methods are inherited.
}