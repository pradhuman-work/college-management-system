package com.collegeManagementSystem.learning.service;

import com.collegeManagementSystem.learning.dto.ProfessorPatchDTO;
import com.collegeManagementSystem.learning.dto.ProfessorRequestDTO;
import com.collegeManagementSystem.learning.dto.ProfessorResponseDTO;
import com.collegeManagementSystem.learning.entity.ProfessorEntity;
import com.collegeManagementSystem.learning.entity.StudentEntity;
import com.collegeManagementSystem.learning.entity.SubjectEntity;
import com.collegeManagementSystem.learning.exception.ResourceNotFoundException;
import com.collegeManagementSystem.learning.repository.ProfessorRepository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ModelMapper mapper;
    private final ProfessorRepository professorRepository;

    public ProfessorResponseDTO createProfessor(ProfessorRequestDTO professorRequestDTO){
        ProfessorEntity professor = requestToEntity(professorRequestDTO);
        ProfessorEntity savedProfessor = professorRepository.save(professor);

        return entityToResponse(savedProfessor);
    }

    @Transactional(readOnly = true)
    public List<ProfessorResponseDTO> getAllProfessors(){
        List<ProfessorEntity> professorEntityList = professorRepository.findAll();
        return professorEntityList
                .stream()
                .map(professorEntity -> {
                    return entityToResponse(professorEntity);
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfessorResponseDTO getProfessorById(Long professorId){
        ProfessorEntity professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id " + professorId));
        return entityToResponse(professor);
    }

    private ProfessorResponseDTO entityToResponse(ProfessorEntity professorEntity){

        ProfessorResponseDTO professorResponseDTO = new ProfessorResponseDTO();
        professorResponseDTO.setId(professorEntity.getId());
        professorResponseDTO.setTitle(professorEntity.getTitle());

        Set<Long> studentIds = professorEntity.getStudents()
                .stream()
                .map(studentEntity -> studentEntity.getId())
                .collect(Collectors.toSet());

        professorResponseDTO.setStudentIds(studentIds);
        return professorResponseDTO;
    }

    private ProfessorEntity requestToEntity(ProfessorRequestDTO professorRequestDTO){
        return mapper.map(professorRequestDTO, ProfessorEntity.class);
    }

    @Transactional
    public ProfessorResponseDTO updateEntireProfessorById(@Valid ProfessorRequestDTO professorRequestDTO, Long professorId) {
        ProfessorEntity professorEntity = professorRepository.findById(professorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Professor Not Found With id : "+professorId)
                );
        professorEntity.setTitle(professorRequestDTO.getTitle());

        ProfessorEntity saved = professorRepository.save(professorEntity);
        return entityToResponse(saved);
    }

    @Transactional
    public ProfessorResponseDTO updateProfessorPartially(ProfessorPatchDTO professorPatchDTO, Long professorId) throws ResourceNotFoundException{
        ProfessorEntity professorEntity = professorRepository.findById(professorId).orElseThrow(()->new ResourceNotFoundException("Professor not found with ID : "+professorId));
        if(professorPatchDTO.getTitle()!=null){
            professorEntity.setTitle(professorPatchDTO.getTitle());
        }
        ProfessorEntity savedProfessor = professorRepository.save(professorEntity);
        return entityToResponse(savedProfessor);
    }

    @Transactional
    public void deleteProfessorById(Long professorId) {
        ProfessorEntity professorEntity = professorRepository.findById(professorId)
                .orElseThrow(()->new ResourceNotFoundException("Professor not found with ID : "+professorId));
        for(SubjectEntity subject : new HashSet<>(professorEntity.getSubjects())){
            professorEntity.removeSubject(subject);
        }
        for(StudentEntity student : new HashSet<>(professorEntity.getStudents())){
            student.removeProfessor(professorEntity);
        }
        professorRepository.delete(professorEntity);
    }
}





















