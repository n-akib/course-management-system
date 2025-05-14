package com.akib.courseManagementSystem.service;

import com.akib.courseManagementSystem.dto.CourseDTO;
import com.akib.courseManagementSystem.entity.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    CourseDTO saveCourse(CourseDTO courseDTO);
    List<CourseDTO> getAllCourses();
    Optional<CourseDTO> getCourseById(Long id);
    CourseDTO updateCourse(Long id, CourseDTO courseDTO);
    void deleteCourse(Long id);
    CourseDTO assignInstructorToCourse(Long courseId, Long instructorId);
    CourseDTO enrollStudentInCourse(Long courseId, Long studentId);
    List<CourseDTO> getCoursesByInstructor(Long instructorId);
    List<Student> getStudentsInCourse(Long courseId);
}