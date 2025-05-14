package com.akib.courseManagementSystem.service;

import com.akib.courseManagementSystem.dto.StudentDTO;

import java.util.List;

/**
 * Service interface for managing Student-related operations.
 * Defines the contract for creating, retrieving, updating, and deleting students.
 */
public interface StudentService {

    /**
     * Creates a new student.
     *
     * @param studentDTO the student data to be created.
     * @return the created StudentDTO with assigned ID and other persisted fields.
     */
    StudentDTO createStudent(StudentDTO studentDTO);

    /**
     * Retrieves a list of all students.
     *
     * @return a list of StudentDTOs representing all students in the system.
     */
    List<StudentDTO> getAllStudents();

    /**
     * Retrieves a student by their ID.
     *
     * @param id the ID of the student to retrieve.
     * @return the StudentDTO representing the student with the given ID.
     */
    StudentDTO getStudentById(Long id);

    /**
     * Updates an existing student's information.
     *
     * @param id         the ID of the student to update.
     * @param studentDTO the updated student data.
     * @return the updated StudentDTO.
     */
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    /**
     * Deletes a student by their ID.
     *
     * @param id the ID of the student to delete.
     */
    void deleteStudent(Long id);
}
