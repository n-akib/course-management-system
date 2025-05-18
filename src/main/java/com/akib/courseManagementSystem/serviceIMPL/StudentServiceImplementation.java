package com.akib.courseManagementSystem.serviceIMPL;

import com.akib.courseManagementSystem.dto.StudentDTO;
import com.akib.courseManagementSystem.entity.Student;
import com.akib.courseManagementSystem.repository.StudentRepository;
import com.akib.courseManagementSystem.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the StudentService interface.
 * Handles business logic related to Student operations.
 */
@Service
public class StudentServiceImplementation implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImplementation.class);
    private final StudentRepository studentRepository;

    /**
     * Constructor for injecting the StudentRepository.
     *
     * @param studentRepository the repository used for student persistence operations
     */
    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        logger.info("Creating student: {}", studentDTO.getName());
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        Student savedStudent = studentRepository.save(student);
        return toDTO(savedStudent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StudentDTO> getAllStudents() {
        logger.info("Fetching all students");
        return studentRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDTO getStudentById(Long id) {
        logger.info("Fetching student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return toDTO(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        logger.info("Updating student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        Student updatedStudent = studentRepository.save(student);
        return toDTO(updatedStudent);
    }

    /**
     * {@inheritDoc}
     */
    /** Changed to soft delete by setting deletedAt
     *
     * @param id the ID of the student to delete.
     */
    @Override
    public void deleteStudent(Long id) {
        logger.info("Soft deleting student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        student.setDeletedAt(LocalDateTime.now());
        studentRepository.save(student);
    }

    /**
     * Converts a Student entity to a StudentDTO.
     *
     * @param student the Student entity
     * @return the corresponding StudentDTO
     */
    private StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        return dto;
    }
}
