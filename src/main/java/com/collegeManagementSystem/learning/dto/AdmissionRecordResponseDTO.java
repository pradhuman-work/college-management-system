package com.collegeManagementSystem.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AdmissionRecordResponseDTO {
    private Long id;
    private Integer fees;
    private Long studentId;
}
