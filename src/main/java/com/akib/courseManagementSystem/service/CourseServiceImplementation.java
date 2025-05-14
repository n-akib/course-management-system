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

@Service
@RequiredArgsConstructor
public class CourseServiceImplementation implements CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImplementation.class);
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        logger.info("Saving course: {}", courseDTO.getTitle());
        Course course = toEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return toDTO(savedCourse);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        logger.info("Fetching all courses");
        return courseRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDTO> getCourseById(Long id) {
        logger.info("Fetching course with ID: {}", id);
        return courseRepository.findById(id).map(this::toDTO);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        logger.info("Updating course with ID: {}", id);
        return courseRepository.findById(id).map(course -> {
            course.setTitle(courseDTO.getTitle());
            course.setDescription(courseDTO.getDescription());
            course.setStartDate(courseDTO.getStartDate());
            course.setEndDate(courseDTO.getEndDate());
            Course updatedCourse = courseRepository.save(course);
            return toDTO(updatedCourse);
        }).orElse(null);
    }

    @Override
    public void deleteCourse(Long id) {
        logger.info("Deleting course with ID: {}", id);
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDTO assignInstructorToCourse(Long courseId, Long instructorId) {
        logger.info("Assigning instructor {} to course {}", instructorId, courseId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + instructorId));
        course.setInstructor(instructor);
        Course updatedCourse = courseRepository.save(course);
        return toDTO(updatedCourse);
    }

    @Override
    public CourseDTO enrollStudentInCourse(Long courseId, Long studentId) {
        logger.info("Enrolling student {} in course {}", studentId, courseId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        course.getStudents().add(student);
        Course updatedCourse = courseRepository.save(course);
        return toDTO(updatedCourse);
    }

    @Override
    public List<CourseDTO> getCoursesByInstructor(Long instructorId) {
        logger.info("Fetching courses for instructor ID: {}", instructorId);
        return courseRepository.findByInstructorId(instructorId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsInCourse(Long courseId) {
        logger.info("Fetching students for course ID: {}", courseId);
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

    private CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setStartDate(course.getStartDate());
        dto.setEndDate(course.getEndDate());
        if (course.getInstructor() != null) {
            dto.setInstructorId(course.getInstructor().getId());
        }
        dto.setStudentIds(course.getStudents().stream()
                .map(Student::getId)
                .collect(Collectors.toList()));
        return dto;
    }

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