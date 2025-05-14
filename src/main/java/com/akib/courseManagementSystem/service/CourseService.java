package com.akib.courseManagementSystem.service;

import com.akib.courseManagementSystem.dto.CourseDTO;
import com.akib.courseManagementSystem.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing course-related operations in the course management system.
 * Defines methods for creating, retrieving, updating, and deleting courses,
 * as well as assigning instructors and enrolling students.
 */
public interface CourseService {
    /**
     * Saves a new course to the system.
     *
     * @param courseDTO The course data transfer object containing course details.
     * @return The saved CourseDTO.
     */
    CourseDTO saveCourse(CourseDTO courseDTO);

    /**
     * Retrieves a list of all courses.
     *
     * @return A list of CourseDTOs representing all courses in the system.
     */
    List<CourseDTO> getAllCourses();

    /**
     * Retrieves a course by its ID.
     *
     * @param id The ID of the course to retrieve.
     * @return An Optional containing the CourseDTO if found, or empty if not.
     */
    Optional<CourseDTO> getCourseById(Long id);

    /**
     * Updates an existing course with the provided details.
     *
     * @param id        The ID of the course to update.
     * @param courseDTO The updated course data transfer object.
     * @return The updated CourseDTO, or null if the course does not exist.
     */
    CourseDTO updateCourse(Long id, CourseDTO courseDTO);

    /**
     * Deletes a course by its ID.
     *
     * @param id The ID of the course to delete.
     */
    void deleteCourse(Long id);

    /**
     * Assigns an instructor to a course.
     *
     * @param courseId     The ID of the course.
     * @param instructorId The ID of the instructor to assign.
     * @return The updated CourseDTO with the assigned instructor.
     */
    CourseDTO assignInstructorToCourse(Long courseId, Long instructorId);

    /**
     * Enrolls a student in a course.
     *
     * @param courseId  The ID of the course.
     * @param studentId The ID of the student to enroll.
     * @return The updated CourseDTO with the enrolled student.
     */
    CourseDTO enrollStudentInCourse(Long courseId, Long studentId);

    /**
     * Retrieves all courses taught by a specific instructor.
     *
     * @param instructorId The ID of the instructor.
     * @return A list of CourseDTOs representing the courses taught by the instructor.
     */
    List<CourseDTO> getCoursesByInstructor(Long instructorId);

    /**
     * Retrieves all students enrolled in a specific course.
     *
     * @param courseId The ID of the course.
     * @return A list of StudentDTOs representing the students enrolled in the course.
     */
    List<StudentDTO> getStudentsInCourse(Long courseId);
}