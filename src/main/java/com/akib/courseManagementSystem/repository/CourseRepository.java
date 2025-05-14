package com.akib.courseManagementSystem.repository;

import com.akib.courseManagementSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Add custom query methods if needed
    List<Course> findByInstructorId(Long instructorId);
}
