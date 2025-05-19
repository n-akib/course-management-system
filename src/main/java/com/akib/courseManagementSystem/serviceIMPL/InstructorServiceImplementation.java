package com.akib.courseManagementSystem.serviceIMPL;

import com.akib.courseManagementSystem.dto.InstructorDTO;
import com.akib.courseManagementSystem.entity.Instructor;
import com.akib.courseManagementSystem.repository.InstructorRepository;
import com.akib.courseManagementSystem.service.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the InstructorService interface.
 * Handles business logic related to instructor management including
 * creating, retrieving, updating, and deleting instructor records.
 */
@Service
public class InstructorServiceImplementation implements InstructorService {

    private static final Logger logger = LoggerFactory.getLogger(InstructorServiceImplementation.class);
    private final InstructorRepository instructorRepository;

    /**
     * Constructor for dependency injection of InstructorRepository.
     *
     * @param instructorRepository the repository used for instructor persistence operations.
     */
    public InstructorServiceImplementation(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        logger.info("Creating instructor: {}", instructorDTO.getName());
        Instructor instructor = new Instructor();
        instructor.setName(instructorDTO.getName());
        instructor.setEmail(instructorDTO.getEmail());
        Instructor savedInstructor = instructorRepository.save(instructor);
        return toDTO(savedInstructor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<InstructorDTO> getAllInstructors(int page, int size) {
        logger.info("Fetching instructors for page: {}, size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<Instructor> instructorPage = instructorRepository.findAll(pageable);
        return instructorPage.map(this::toDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InstructorDTO getInstructorById(Long id) {
        logger.info("Fetching instructor with ID: {}", id);
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + id));
        return toDTO(instructor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InstructorDTO updateInstructor(Long id, InstructorDTO instructorDTO) {
        logger.info("Updating instructor with ID: {}", id);
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + id));
        instructor.setName(instructorDTO.getName());
        instructor.setEmail(instructorDTO.getEmail());
        Instructor updatedInstructor = instructorRepository.save(instructor);
        return toDTO(updatedInstructor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteInstructor(Long id) {
        logger.info("Soft deleting instructor with ID: {}", id);
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + id));
        instructor.setDeletedAt(LocalDateTime.now());
        instructorRepository.save(instructor);
    }

    /**
     * Converts an Instructor entity to a DTO.
     *
     * @param instructor the Instructor entity to convert.
     * @return the corresponding InstructorDTO.
     */
    private InstructorDTO toDTO(Instructor instructor) {
        InstructorDTO dto = new InstructorDTO();
        dto.setId(instructor.getId());
        dto.setName(instructor.getName());
        dto.setEmail(instructor.getEmail());
        return dto;
    }
}
