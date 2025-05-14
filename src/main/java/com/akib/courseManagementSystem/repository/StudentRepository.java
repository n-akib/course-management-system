package com.akib.courseManagementSystem.repository;

import com.akib.courseManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Student entities.
 * Extends JpaRepository to provide standard database operations for Student entities.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // No custom query methods defined; standard JpaRepository methods are inherited.
}