package com.collegeManagementSystem.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorResponseDTO {
    private Long id;
    private String title;
    private Set<Long> studentIds = new HashSet<>();
}
