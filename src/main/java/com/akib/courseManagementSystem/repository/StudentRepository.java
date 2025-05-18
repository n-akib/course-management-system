package com.akib.courseManagementSystem.repository;

import com.akib.courseManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on Student entities.
 * Extends JpaRepository to provide standard database operations for Student entities.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // No custom query methods defined; standard JpaRepository methods are inherited.

    // Updated findAll to exclude soft-deleted students
    @Query("SELECT s FROM Student s WHERE s.deletedAt IS NULL")
    List<Student> findAll();

    // Updated findById to exclude soft-deleted students
    @Query("SELECT s FROM Student s WHERE s.id = :id AND s.deletedAt IS NULL")
    Optional<Student> findById(Long id);
}