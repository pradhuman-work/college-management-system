package com.collegeManagementSystem.learning.service;

import com.collegeManagementSystem.learning.dto.AdmissionRecordPatchDTO;
import com.collegeManagementSystem.learning.dto.AdmissionRecordRequestDTO;
import com.collegeManagementSystem.learning.dto.AdmissionRecordResponseDTO;
import com.collegeManagementSystem.learning.entity.AdmissionRecordEntity;
import com.collegeManagementSystem.learning.repository.AdmissionRecordRepository;
import com.collegeManagementSystem.learning.entity.StudentEntity;
import com.collegeManagementSystem.learning.exception.ResourceAlreadyExistsException;
import com.collegeManagementSystem.learning.exception.ResourceNotFoundException;
import com.collegeManagementSystem.learning.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdmissionRecordService {

    private final StudentRepository studentRepository;
    private final AdmissionRecordRepository admissionRecordRepository;

    @Transactional
    public AdmissionRecordResponseDTO createAdmissionRecord(Long studentId, AdmissionRecordRequestDTO dto) throws ResourceAlreadyExistsException {
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found with id : "+studentId));
        if(studentEntity.getAdmissionRecord()!=null){
            throw new ResourceAlreadyExistsException("Admission record already exists in the system.");
        }

        AdmissionRecordEntity admissionRecordEntity = new AdmissionRecordEntity();
        admissionRecordEntity.setFees(dto.getFees());
        studentEntity.assignAdmissionRecord(admissionRecordEntity);
        admissionRecordRepository.save(admissionRecordEntity);
        return entityToResponse(admissionRecordEntity);
    }

    @Transactional(readOnly = true)
    public AdmissionRecordResponseDTO getAdmissionRecord(Long studentId){
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found with id : "+studentId));
        getRecordOrThrow(studentEntity);
        return entityToResponse(studentEntity.getAdmissionRecord());
    }

    @Transactional
    public AdmissionRecordResponseDTO updateAdmissionRecord(Long studentId, AdmissionRecordRequestDTO dto){
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found with id : "+studentId));
        getRecordOrThrow(studentEntity);
        studentEntity.getAdmissionRecord().setFees(dto.getFees());
        return entityToResponse(studentEntity.getAdmissionRecord());
    }

    @Transactional
    public AdmissionRecordResponseDTO patchAdmissionRecord(Long studentId, AdmissionRecordPatchDTO dto){
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found with id : "+studentId));
        getRecordOrThrow(studentEntity);
        if(dto.getFees()!=null){
            studentEntity.getAdmissionRecord().setFees(dto.getFees());
        }
        return entityToResponse(studentEntity.getAdmissionRecord());
    }

    @Transactional
    public void deleteAdmissionRecord(Long studentId){
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found with id : "+studentId));
        getRecordOrThrow(studentEntity);
        studentEntity.setAdmissionRecord(null);
    }

    private AdmissionRecordEntity getRecordOrThrow(StudentEntity studentEntity){
        AdmissionRecordEntity record = studentEntity.getAdmissionRecord();
        if(record == null){
            throw new ResourceNotFoundException("Student " + studentEntity.getName() + " with id " + studentEntity.getId() + " has no admission record");
        }
        return record;
    }

    private AdmissionRecordResponseDTO entityToResponse(AdmissionRecordEntity admissionRecord){
        AdmissionRecordResponseDTO admissionRecordResponseDTO = new AdmissionRecordResponseDTO();
        admissionRecordResponseDTO.setId(admissionRecord.getId());
        admissionRecordResponseDTO.setFees(admissionRecord.getFees());
        admissionRecordResponseDTO.setStudentId(admissionRecord.getStudent().getId());
        return admissionRecordResponseDTO;
    }
}
