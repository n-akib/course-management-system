package com.akib.courseManagementSystem.controller;

import com.akib.courseManagementSystem.dto.CourseDTO;
import com.akib.courseManagementSystem.dto.StudentDTO;
import com.akib.courseManagementSystem.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.saveCourse(courseDTO));
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);
        return updatedCourse != null ? ResponseEntity.ok(updatedCourse) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{courseId}/instructor/{instructorId}")
    public ResponseEntity<CourseDTO> assignInstructor(@PathVariable Long courseId, @PathVariable Long instructorId) {
        return ResponseEntity.ok(courseService.assignInstructorToCourse(courseId, instructorId));
    }

    @PostMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<CourseDTO> enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        return ResponseEntity.ok(courseService.enrollStudentInCourse(courseId, studentId));
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<CourseDTO>> getCoursesByInstructor(@PathVariable Long instructorId) {
        return ResponseEntity.ok(courseService.getCoursesByInstructor(instructorId));
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsInCourse(@PathVariable Long courseId) {
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