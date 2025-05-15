package com.akib.courseManagementSystem.repository;

import com.akib.courseManagementSystem.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on Instructor entities.
 * Extends JpaRepository to provide standard database operations for Instructor entities.
 */
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    // No custom query methods defined; standard JpaRepository methods are inherited.

    @Query("SELECT i FROM Instructor i WHERE i.deletedAt IS NULL")
    List<Instructor> findAll();

    @Query("SELECT i FROM Instructor i WHERE i.id = :id AND i.deletedAt IS NULL")
    Optional<Instructor> findById(Long id);
}