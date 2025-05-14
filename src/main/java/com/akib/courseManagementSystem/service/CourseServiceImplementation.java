package com.akib.courseManagementSystem.service;

import com.akib.courseManagementSystem.entity.Course;
import com.akib.courseManagementSystem.repository.CourseRepository;
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
}