package com.collegeManagementSystem.learning.controller;

import com.collegeManagementSystem.learning.dto.ProfessorPatchDTO;
import com.collegeManagementSystem.learning.dto.ProfessorRequestDTO;
import com.collegeManagementSystem.learning.dto.ProfessorResponseDTO;
import com.collegeManagementSystem.learning.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> createProfessor(@Valid @RequestBody ProfessorRequestDTO professorRequestDTO){
        ProfessorResponseDTO professorResponseDTO = professorService.createProfessor(professorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> getAllProfessor(){
        List<ProfessorResponseDTO> list = professorService.getAllProfessors();
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponseDTO> getProfessorById(@PathVariable(name = "id") Long professorId){
        ProfessorResponseDTO professorResponseDTO = professorService.getProfessorById(professorId);
        return ResponseEntity.ok(professorResponseDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponseDTO> updateProfessorById(@Valid @RequestBody ProfessorRequestDTO professorRequestDTO, @PathVariable(name = "id") Long professorId){
        ProfessorResponseDTO professorResponseDTO = professorService.updateEntireProfessorById(professorRequestDTO, professorId);
        return ResponseEntity.status(HttpStatus.OK).body(professorResponseDTO);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ProfessorResponseDTO> updateProfessorPartially(
            @RequestBody ProfessorPatchDTO professorPatchDTO,
            @PathVariable(name="id") Long professorId){
        ProfessorResponseDTO professorResponseDTO = professorService.updateProfessorPartially(professorPatchDTO, professorId);
        return ResponseEntity.ok(professorResponseDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProfessorEntity(@PathVariable(name = "id") Long professorId){
        professorService.deleteProfessorById(professorId);
        return ResponseEntity.noContent().build();
    }
}
