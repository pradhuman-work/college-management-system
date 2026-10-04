package com.collegeManagementSystem.learning.controller;

import com.collegeManagementSystem.learning.dto.SubjectPatchDTO;
import com.collegeManagementSystem.learning.dto.SubjectRequestDTO;
import com.collegeManagementSystem.learning.dto.SubjectResponseDTO;
import com.collegeManagementSystem.learning.repository.SubjectRepository;
import com.collegeManagementSystem.learning.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects(){
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SubjectResponseDTO> getSubjectById(@PathVariable(name = "id") Long subjectId){
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubjectById(subjectId));
    }

    @PostMapping
    public ResponseEntity<SubjectResponseDTO> createSubject(@RequestBody @Valid SubjectRequestDTO subjectRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(subjectRequestDTO));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SubjectResponseDTO> updateSubject(@PathVariable(name = "id") Long subjectId,
                                                            @Valid @RequestBody SubjectRequestDTO subjectRequestDTO){
        return ResponseEntity.ok(subjectService.updateSubject(subjectId, subjectRequestDTO));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<SubjectResponseDTO> patchSubjectDetails(@PathVariable(name = "id") Long subjectId,
                                                                  @RequestBody SubjectPatchDTO subjectPatchDTO){
        return ResponseEntity.ok(subjectService.patchSubject(subjectId, subjectPatchDTO));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable(name = "id") Long subjectId){
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{subjectId}/professor/{professorId}")
    public ResponseEntity<SubjectResponseDTO> assignProfessorToSubject(@PathVariable Long subjectId,
                                                                       @PathVariable Long professorId){
        return ResponseEntity.ok(subjectService.assignProfessor(subjectId, professorId));
    }

    @DeleteMapping(path = "/{subjectId}/professor")
    public ResponseEntity<SubjectResponseDTO> unassignProfessorFromSubject(@PathVariable Long subjectId){
        return ResponseEntity.ok(subjectService.unassignProfessor(subjectId));
    }


}









