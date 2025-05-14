package com.akib.courseManagementSystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long instructorId;
    private List<Long> studentIds;
}