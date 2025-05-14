package com.akib.courseManagementSystem.controller;

import com.akib.courseManagementSystem.dto.CourseDTO;
import com.akib.courseManagementSystem.dto.StudentDTO;
import com.akib.courseManagementSystem.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing courses in the course management system.
 * Provides endpoints for creating, retrieving, updating, and deleting courses,
 * as well as assigning instructors and enrolling students.
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    /**
     * Constructor for dependency injection of the CourseService.
     *
     * @param courseService The service layer for course-related operations.
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Creates a new course.
     *
     * @param courseDTO The course data transfer object containing course details.
     * @return ResponseEntity containing the created CourseDTO with HTTP status 200 (OK).
     */
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.saveCourse(courseDTO));
    }

    /**
     * Retrieves a list of all courses.
     *
     * @return ResponseEntity containing a list of CourseDTOs with HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param id The ID of the course to retrieve.
     * @return ResponseEntity containing the CourseDTO if found, or HTTP status 404 (Not Found) if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates an existing course by its ID.
     *
     * @param id        The ID of the course to update.
     * @param courseDTO The updated course data transfer object.
     * @return ResponseEntity containing the updated CourseDTO if successful, or HTTP status 404 (Not Found) if the course does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);
        return updatedCourse != null ? ResponseEntity.ok(updatedCourse) : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a course by its ID.
     *
     * @param id The ID of the course to delete.
     * @return ResponseEntity with HTTP status 204 (No Content) upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Assigns an instructor to a course.
     *
     * @param courseId     The ID of the course.
     * @param instructorId The ID of the instructor to assign.
     * @return ResponseEntity containing the updated CourseDTO with HTTP status 200 (OK).
     */
    @PostMapping("/{courseId}/instructor/{instructorId}")
    public ResponseEntity<CourseDTO> assignInstructor(@PathVariable Long courseId, @PathVariable Long instructorId) {
        return ResponseEntity.ok(courseService.assignInstructorToCourse(courseId, instructorId));
    }

    /**
     * Enrolls a student in a course.
     *
     * @param courseId  The ID of the course.
     * @param studentId The ID of the student to enroll.
     * @return ResponseEntity containing the updated CourseDTO with HTTP status 200 (OK).
     */
    @PostMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<CourseDTO> enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        return ResponseEntity.ok(courseService.enrollStudentInCourse(courseId, studentId));
    }

    /**
     * Retrieves all courses taught by a specific instructor.
     *
     * @param instructorId The ID of the instructor.
     * @return ResponseEntity containing a list of CourseDTOs with HTTP status 200 (OK).
     */
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<CourseDTO>> getCoursesByInstructor(@PathVariable Long instructorId) {
        return ResponseEntity.ok(courseService.getCoursesByInstructor(instructorId));
    }

    /**
     * Retrieves all students enrolled in a specific course.
     *
     * @param courseId The ID of the course.
     * @return ResponseEntity containing a list of StudentDTOs with HTTP status 200 (OK).
     */
    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsInCourse(@PathVariable Long courseId) {
        // Convert Student entities to StudentDTOs for response
        return ResponseEntity.ok(courseService.getStudentsInCourse(courseId).stream()
                .map(student -> {
                    StudentDTO dto = new StudentDTO();
                    dto.setId(student.getId());
                    dto.setName(student.getName());
                    dto.setEmail(student.getEmail());
                    return dto;
                })
                .collect(Collectors.toList()));
    }
}