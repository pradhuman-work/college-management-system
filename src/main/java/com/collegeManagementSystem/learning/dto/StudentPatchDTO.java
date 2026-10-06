package com.collegeManagementSystem.learning.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentPatchDTO {
    @Pattern(regexp = ".*\\S.*")
    private String name;
}
