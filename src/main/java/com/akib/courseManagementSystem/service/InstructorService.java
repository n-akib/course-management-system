package com.akib.courseManagementSystem.service;

import com.akib.courseManagementSystem.model.Instructor;

import java.util.List;

public interface InstructorService {
    Instructor createInstructor(Instructor instructor);
    List<Instructor> getAllInstructors();
    Instructor getInstructorById(Long id);
    Instructor updateInstructor(Long id, Instructor instructor);
    void deleteInstructor(Long id);
}
