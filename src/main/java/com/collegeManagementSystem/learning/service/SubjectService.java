package com.collegeManagementSystem.learning.service;

import com.collegeManagementSystem.learning.dto.SubjectPatchDTO;
import com.collegeManagementSystem.learning.dto.SubjectRequestDTO;
import com.collegeManagementSystem.learning.dto.SubjectResponseDTO;
import com.collegeManagementSystem.learning.entity.ProfessorEntity;
import com.collegeManagementSystem.learning.entity.SubjectEntity;
import com.collegeManagementSystem.learning.exception.ResourceNotFoundException;
import com.collegeManagementSystem.learning.repository.ProfessorRepository;
import com.collegeManagementSystem.learning.repository.SubjectRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final ModelMapper modelMapper;
    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;

    public SubjectResponseDTO createSubject(SubjectRequestDTO subjectRequestDTO){
        SubjectEntity toBeSaveSubject = modelMapper.map(subjectRequestDTO, SubjectEntity.class);
        SubjectEntity savedEntity = subjectRepository.save(toBeSaveSubject);
        return entityToResponse(savedEntity);
    }

    private SubjectResponseDTO entityToResponse(SubjectEntity subjectEntity){
        SubjectResponseDTO dto = modelMapper.map(subjectEntity, SubjectResponseDTO.class);
        dto.setProfessorId(subjectEntity.getProfessor()!=null ? subjectEntity.getProfessor().getId():null);
        return dto;
    }

    public List<SubjectResponseDTO> getAllSubjects() {
        List<SubjectEntity> subjectEntities = subjectRepository.findAll();
        return subjectEntities
                .stream()
                .map((subjectEntity)->
                    entityToResponse(subjectEntity)
                )
                .collect(Collectors.toList());
    }

    public SubjectResponseDTO getSubjectById(Long subjectId) {
        SubjectEntity subjectEntity = subjectRepository
                .findById(subjectId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Subject Not Found with id : "+subjectId)
                );
        return entityToResponse(subjectEntity);
    }

    @Transactional
    public SubjectResponseDTO updateSubject(Long subjectId, SubjectRequestDTO subjectRequestDTO) {
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId)
                .orElseThrow(()->new ResourceNotFoundException("Subject not found with Id : "+subjectId));
        subjectEntity.setTitle(subjectRequestDTO.getTitle());
        SubjectEntity savedEntity = subjectRepository.save(subjectEntity);
        return entityToResponse(savedEntity);
    }

    @Transactional
    public SubjectResponseDTO patchSubject(Long subjectId, SubjectPatchDTO subjectPatchDTO){
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElseThrow(()->new ResourceNotFoundException("Subject Not found with Id : "+subjectId));
        if(subjectPatchDTO.getTitle()!=null){
            subjectEntity.setTitle(subjectPatchDTO.getTitle());
        }
        return entityToResponse(subjectRepository.save(subjectEntity));
    }

    @Transactional
    public void deleteSubject(Long subjectId){
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElseThrow(()->new ResourceNotFoundException("Subject Not found with Id : "+subjectId));
        if(subjectEntity.getProfessor()!=null){
            subjectEntity.getProfessor().removeSubject(subjectEntity);
        }
        subjectRepository.delete(subjectEntity);
    }

    @Transactional
    public SubjectResponseDTO assignProfessor(Long subjectId, Long professorId){
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElseThrow(()->new ResourceNotFoundException("Subject not found with Id : "+subjectId));
        ProfessorEntity professorEntity = professorRepository.findById(professorId).orElseThrow(()->new ResourceNotFoundException("Professor not found with Id : "+professorId));

        professorEntity.addSubject(subjectEntity);

        return entityToResponse(subjectEntity);
    }

    @Transactional
    public SubjectResponseDTO unassignProfessor(Long subjectId){
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElseThrow(()->new ResourceNotFoundException("Subject not found with Id : "+subjectId));
        if(subjectEntity.getProfessor()!=null){
            subjectEntity.getProfessor().removeSubject(subjectEntity);
        }
        return entityToResponse(subjectEntity);
    }

}





















