package com.collegeManagementSystem.learning.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponseDTO {
    private Long id;
    private String name;
    private Set<Long> subjectIds = new HashSet<>();
    private Set<Long> professorIds = new HashSet<>();
    private Long admissionRecordId;
}
