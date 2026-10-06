package com.collegeManagementSystem.learning.controller;

import com.collegeManagementSystem.learning.dto.AdmissionRecordPatchDTO;
import com.collegeManagementSystem.learning.dto.AdmissionRecordRequestDTO;
import com.collegeManagementSystem.learning.dto.AdmissionRecordResponseDTO;
import com.collegeManagementSystem.learning.service.AdmissionRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/students/{studentId}/admission-record")
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @PostMapping
    public ResponseEntity<AdmissionRecordResponseDTO> createAdmissionRecord(
            @PathVariable(name = "studentId") Long id,
            @Valid @RequestBody AdmissionRecordRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(admissionRecordService.createAdmissionRecord(id, dto));
    }

    @GetMapping
    public ResponseEntity<AdmissionRecordResponseDTO> getAdmissionRecord(@PathVariable(name = "studentId") Long id){
        return ResponseEntity.ok(admissionRecordService.getAdmissionRecord(id));
    }

    @PutMapping
    public ResponseEntity<AdmissionRecordResponseDTO> putAdmissionRecord(
            @PathVariable(name = "studentId") Long id,
            @Valid @RequestBody AdmissionRecordRequestDTO dto){
        return ResponseEntity.ok(admissionRecordService.updateAdmissionRecord(id, dto));
    }

    @PatchMapping
    public ResponseEntity<AdmissionRecordResponseDTO> patchAdmissionRecord(
            @PathVariable(name = "studentId") Long id,
            @Valid @RequestBody AdmissionRecordPatchDTO dto){
        return ResponseEntity.ok(admissionRecordService.patchAdmissionRecord(id, dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAdmissionRecord(@PathVariable(name = "studentId") Long id){
        admissionRecordService.deleteAdmissionRecord(id);
        return ResponseEntity.noContent().build();
    }
}
