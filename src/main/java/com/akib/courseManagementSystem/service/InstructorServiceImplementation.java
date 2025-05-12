package com.akib.courseManagementSystem.service;

import com.akib.courseManagementSystem.model.Instructor;
import com.akib.courseManagementSystem.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImplementation implements InstructorService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImplementation(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + id));
    }

    @Override
    public Instructor updateInstructor(Long id, Instructor updatedInstructor) {
        Instructor instructor = getInstructorById(id);
        instructor.setName(updatedInstructor.getName());
        instructor.setEmail(updatedInstructor.getEmail());
        return instructorRepository.save(instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}
