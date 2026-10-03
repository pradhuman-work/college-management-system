package com.collegeManagementSystem.learning.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorRequestDTO {
    @NotBlank
    private String title;
}
