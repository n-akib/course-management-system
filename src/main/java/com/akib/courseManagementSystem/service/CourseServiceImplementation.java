package com.akib.courseManagementSystem.service;

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

@Service
@RequiredArgsConstructor
public class CourseServiceImplementation implements CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImplementation.class);
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    @Override
    public Course saveCourse(Course course) {
        logger.info("Saving course: {}", course.getTitle());
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        logger.info("Fetching all courses");
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        logger.info("Fetching course with ID: {}", id);
        return courseRepository.findById(id);
    }

    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        logger.info("Updating course with ID: {}", id);
        return courseRepository.findById(id).map(course -> {
            course.setTitle(updatedCourse.getTitle());
            course.setDescription(updatedCourse.getDescription());
            course.setStartDate(updatedCourse.getStartDate());
            course.setEndDate(updatedCourse.getEndDate());
            return courseRepository.save(course);
        }).orElse(null);
    }

    @Override
    public void deleteCourse(Long id) {
        logger.info("Deleting course with ID: {}", id);
        courseRepository.deleteById(id);
    }

    @Override
    public Course assignInstructorToCourse(Long courseId, Long instructorId) {
        logger.info("Assigning instructor {} to course {}", instructorId, courseId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + instructorId));
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @Override
    public Course enrollStudentInCourse(Long courseId, Long studentId) {
        logger.info("Enrolling student {} in course {}", studentId, courseId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        course.getStudents().add(student);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getCoursesByInstructor(Long instructorId) {
        logger.info("Fetching courses for instructor ID: {}", instructorId);
        return courseRepository.findByInstructorId(instructorId);
    }

    @Override
    public List<Student> getStudentsInCourse(Long courseId) {
        logger.info("Fetching students for course ID: {}", courseId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        return course.getStudents();
    }
}