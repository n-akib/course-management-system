package com.akib.courseManagementSystem.controller;

import com.akib.courseManagementSystem.dto.InstructorDTO;
import com.akib.courseManagementSystem.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing instructors in the course management system.
 * Provides endpoints for creating, retrieving, updating, and deleting instructors.
 */
@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    /**
     * Constructor for dependency injection of the InstructorService.
     *
     * @param instructorService The service layer for instructor-related operations.
     */
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    /**
     * Creates a new instructor.
     *
     * @param instructorDTO The instructor data transfer object containing instructor details.
     * @return ResponseEntity containing the created InstructorDTO with HTTP status 200 (OK).
     */
    @PostMapping
    public ResponseEntity<InstructorDTO> createInstructor(@RequestBody InstructorDTO instructorDTO) {
        return ResponseEntity.ok(instructorService.createInstructor(instructorDTO));
    }

    /**
     * Retrieves a list of all instructors.
     *
     * @return ResponseEntity containing a list of InstructorDTOs with HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getAllInstructors() {
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }

    /**
     * Retrieves an instructor by their ID.
     *
     * @param id The ID of the instructor to retrieve.
     * @return ResponseEntity containing the InstructorDTO with HTTP status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable Long id) {
        return ResponseEntity.ok(instructorService.getInstructorById(id));
    }

    /**
     * Updates an existing instructor by their ID.
     *
     * @param id            The ID of the instructor to update.
     * @param instructorDTO The updated instructor data transfer object.
     * @return ResponseEntity containing the updated InstructorDTO with HTTP status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable Long id, @RequestBody InstructorDTO instructorDTO) {
        return ResponseEntity.ok(instructorService.updateInstructor(id, instructorDTO));
    }

    /**
     * Deletes an instructor by their ID.
     *
     * @param id The ID of the instructor to delete.
     * @return ResponseEntity with HTTP status 204 (No Content) upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
}