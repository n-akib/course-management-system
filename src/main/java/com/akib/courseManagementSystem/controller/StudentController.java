package com.akib.courseManagementSystem.controller;

import com.akib.courseManagementSystem.dto.StudentDTO;
import com.akib.courseManagementSystem.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing students in the course management system.
 * Provides endpoints for creating, retrieving, updating, and deleting students.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    /**
     * Constructor for dependency injection of the StudentService.
     *
     * @param studentService The service layer for student-related operations.
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Creates a new student.
     *
     * @param studentDTO The student data transfer object containing student details.
     * @return ResponseEntity containing the created StudentDTO with HTTP status 200 (OK).
     */
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.createStudent(studentDTO));
    }

    /**
     * Retrieves a list of all students.
     *
     * @return ResponseEntity containing a list of StudentDTOs with HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param id The ID of the student to retrieve.
     * @return ResponseEntity containing the StudentDTO with HTTP status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    /**
     * Updates an existing student by their ID.
     *
     * @param id         The ID of the student to update.
     * @param studentDTO The updated student data transfer object.
     * @return ResponseEntity containing the updated StudentDTO with HTTP status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTO));
    }

    /**
     * Deletes a student by their ID.
     *
     * @param id The ID of the student to delete.
     * @return ResponseEntity with HTTP status 204 (No Content) upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}