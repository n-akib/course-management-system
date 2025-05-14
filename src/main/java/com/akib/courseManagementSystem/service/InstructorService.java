package com.akib.courseManagementSystem.service;

//import com.akib.courseManagementSystem.entity.Instructor;
import com.akib.courseManagementSystem.dto.InstructorDTO;

import java.util.List;

public interface InstructorService {
    InstructorDTO createInstructor(InstructorDTO instructorDTO);
    List<InstructorDTO> getAllInstructors();
    InstructorDTO getInstructorById(Long id);
    InstructorDTO updateInstructor(Long id, InstructorDTO instructor);
    void deleteInstructor(Long id);
}
