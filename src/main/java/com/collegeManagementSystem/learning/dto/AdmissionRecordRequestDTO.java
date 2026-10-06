package com.collegeManagementSystem.learning.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdmissionRecordRequestDTO {
    @NotNull
    @PositiveOrZero
    private Integer fees;
}
