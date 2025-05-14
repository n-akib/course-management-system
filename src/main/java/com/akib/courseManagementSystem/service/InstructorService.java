package com.akib.courseManagementSystem.service;

import com.akib.courseManagementSystem.dto.InstructorDTO;

import java.util.List;

/**
 * Service interface for managing instructor-related operations.
 * Provides methods for creating, retrieving, updating, and deleting instructors.
 */
public interface InstructorService {

    /**
     * Creates a new instructor.
     *
     * @param instructorDTO The data transfer object containing instructor details.
     * @return The saved InstructorDTO.
     */
    InstructorDTO createInstructor(InstructorDTO instructorDTO);

    /**
     * Retrieves all instructors in the system.
     *
     * @return A list of InstructorDTOs.
     */
    List<InstructorDTO> getAllInstructors();

    /**
     * Retrieves a specific instructor by ID.
     *
     * @param id The ID of the instructor to retrieve.
     * @return The InstructorDTO with the specified ID, or null if not found.
     */
    InstructorDTO getInstructorById(Long id);

    /**
     * Updates an existing instructor's details.
     *
     * @param id            The ID of the instructor to update.
     * @param instructorDTO The updated instructor data.
     * @return The updated InstructorDTO, or null if the instructor does not exist.
     */
    InstructorDTO updateInstructor(Long id, InstructorDTO instructorDTO);

    /**
     * Deletes an instructor by ID.
     *
     * @param id The ID of the instructor to delete.
     */
    void deleteInstructor(Long id);
}
