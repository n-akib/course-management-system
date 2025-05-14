package com.akib.courseManagementSystem.service;

import com.akib.courseManagementSystem.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course saveCourse(Course course);
    List<Course> getAllCourses();
    Optional<Course> getCourseById(Long id);
    Course updateCourse(Long id, Course updatedCourse);
    void deleteCourse(Long id);
}