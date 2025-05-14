package com.akib.courseManagementSystem.service;

import com.akib.courseManagementSystem.dto.InstructorDTO;
import com.akib.courseManagementSystem.entity.Instructor;
import com.akib.courseManagementSystem.repository.InstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorServiceImplementation implements InstructorService {
    private static final Logger logger = LoggerFactory.getLogger(InstructorServiceImplementation.class);
    private final InstructorRepository instructorRepository;

    public InstructorServiceImplementation(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        logger.info("Creating instructor: {}", instructorDTO.getName());
        Instructor instructor = new Instructor();
        instructor.setName(instructorDTO.getName());
        instructor.setEmail(instructorDTO.getEmail());
        Instructor savedInstructor = instructorRepository.save(instructor);
        return toDTO(savedInstructor);
    }

    @Override
    public List<InstructorDTO> getAllInstructors() {
        logger.info("Fetching all instructors");
        return instructorRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InstructorDTO getInstructorById(Long id) {
        logger.info("Fetching instructor with ID: {}", id);
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + id));
        return toDTO(instructor);
    }

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

    @Override
    public void deleteInstructor(Long id) {
        logger.info("Deleting instructor with ID: {}", id);
        instructorRepository.deleteById(id);
    }

    private InstructorDTO toDTO(Instructor instructor) {
        InstructorDTO dto = new InstructorDTO();
        dto.setId(instructor.getId());
        dto.setName(instructor.getName());
        dto.setEmail(instructor.getEmail());
        return dto;
    }
}