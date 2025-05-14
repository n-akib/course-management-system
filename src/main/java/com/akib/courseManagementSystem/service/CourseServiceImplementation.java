package com.akib.courseManagementSystem.service;

import com.akib.courseManagementSystem.dto.CourseDTO;
import com.akib.courseManagementSystem.dto.StudentDTO;
import com.akib.courseManagementSystem.entity.Course;
import com.akib.courseManagementSystem.entity.Instructor;
import com.akib.courseManagementSystem.entity.Student;
import com.akib.courseManagementSystem.repository.CourseRepository;
import com.akib.courseManagementSystem.repository.InstructorRepository;
import com.akib.courseManagementSystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the CourseService interface for managing course-related operations.
 * Handles CRUD operations, instructor assignments, and student enrollments for courses,
 * including conversion between Course entities and CourseDTOs.
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImplementation implements CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImplementation.class);
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    /**
     * Saves a new course to the system.
     *
     * @param courseDTO The course data transfer object containing course details.
     * @return The saved CourseDTO.
     */
    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        logger.info("Saving course: {}", courseDTO.getTitle());
        // Convert DTO to entity and save to the database
        Course course = toEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return toDTO(savedCourse);
    }

    /**
     * Retrieves a list of all courses.
     *
     * @return A list of CourseDTOs representing all courses in the system.
     */
    @Override
    public List<CourseDTO> getAllCourses() {
        logger.info("Fetching all courses");
        // Fetch all courses and convert to DTOs
        return courseRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param id The ID of the course to retrieve.
     * @return An Optional containing the CourseDTO if found, or empty if not.
     */
    @Override
    public Optional<CourseDTO> getCourseById(Long id) {
        logger.info("Fetching course with ID: {}", id);
        // Fetch course by ID and convert to DTO if found
        return courseRepository.findById(id).map(this::toDTO);
    }

    /**
     * Updates an existing course with the provided details.
     *
     * @param id        The ID of the course to update.
     * @param courseDTO The updated course data transfer object.
     * @return The updated CourseDTO, or null if the course does not exist.
     */
    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        logger.info("Updating course with ID: {}", id);
        // Find course, update fields, save, and convert to DTO
        return courseRepository.findById(id).map(course -> {
            course.setTitle(courseDTO.getTitle());
            course.setDescription(courseDTO.getDescription());
            course.setStartDate(courseDTO.getStartDate());
            course.setEndDate(courseDTO.getEndDate());
            Course updatedCourse = courseRepository.save(course);
            return toDTO(updatedCourse);
        }).orElse(null);
    }

    /**
     * Deletes a course by its ID.
     *
     * @param id The ID of the course to delete.
     */
    @Override
    public void deleteCourse(Long id) {
        logger.info("Deleting course with ID: {}", id);
        // Delete course from the database
        courseRepository.deleteById(id);
    }

    /**
     * Assigns an instructor to a course.
     *
     * @param courseId     The ID of the course.
     * @param instructorId The ID of the instructor to assign.
     * @return The updated CourseDTO with the assigned instructor.
     * @throws RuntimeException if the course or instructor is not found.
     */
    @Override
    public CourseDTO assignInstructorToCourse(Long courseId, Long instructorId) {
        logger.info("Assigning instructor {} to course {}", instructorId, courseId);
        // Fetch course and instructor, validate existence
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + instructorId));
        // Assign instructor to course and save
        course.setInstructor(instructor);
        Course updatedCourse = courseRepository.save(course);
        return toDTO(updatedCourse);
    }

    /**
     * Enrolls a student in a course.
     *
     * @param courseId  The ID of the course.
     * @param studentId The ID of the student to enroll.
     * @return The updated CourseDTO with the enrolled student.
     * @throws RuntimeException if the course or student is not found.
     */
    @Override
    public CourseDTO enrollStudentInCourse(Long courseId, Long studentId) {
        logger.info("Enrolling student {} in course {}", studentId, courseId);
        // Fetch course and student, validate existence
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        // Add student to course's student list and save
        course.getStudents().add(student);
        Course updatedCourse = courseRepository.save(course);
        return toDTO(updatedCourse);
    }

    /**
     * Retrieves all courses taught by a specific instructor.
     *
     * @param instructorId The ID of the instructor.
     * @return A list of CourseDTOs representing the courses taught by the instructor.
     */
    @Override
    public List<CourseDTO> getCoursesByInstructor(Long instructorId) {
        logger.info("Fetching courses for instructor ID: {}", instructorId);
        // Fetch courses by instructor ID and convert to DTOs
        return courseRepository.findByInstructorId(instructorId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all students enrolled in a specific course.
     *
     * @param courseId The ID of the course.
     * @return A list of StudentDTOs representing the students enrolled in the course.
     * @throws RuntimeException if the course is not found.
     */
    @Override
    public List<StudentDTO> getStudentsInCourse(Long courseId) {
        logger.info("Fetching students for course ID: {}", courseId);
        // Fetch course, validate existence, and convert its students to DTOs
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        return course.getStudents().stream()
                .map(student -> {
                    StudentDTO dto = new StudentDTO();
                    dto.setId(student.getId());
                    dto.setName(student.getName());
                    dto.setEmail(student.getEmail());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * Converts a Course entity to a CourseDTO.
     *
     * @param course The Course entity to convert.
     * @return The corresponding CourseDTO with instructor and student IDs.
     */
    private CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setStartDate(course.getStartDate());
        dto.setEndDate(course.getEndDate());
        // Set instructor ID if instructor exists
        if (course.getInstructor() != null) {
            dto.setInstructorId(course.getInstructor().getId());
        }
        // Set list of student IDs
        dto.setStudentIds(course.getStudents().stream()
                .map(Student::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    /**
     * Converts a CourseDTO to a Course entity.
     *
     * @param courseDTO The CourseDTO to convert.
     * @return The corresponding Course entity.
     */
    private Course toEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setStartDate(courseDTO.getStartDate());
        course.setEndDate(courseDTO.getEndDate());
        return course;
    }
}