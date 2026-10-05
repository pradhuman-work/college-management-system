package com.collegeManagementSystem.learning.service;

import com.collegeManagementSystem.learning.dto.StudentRequestDTO;
import com.collegeManagementSystem.learning.dto.StudentResponseDTO;
import com.collegeManagementSystem.learning.entity.StudentEntity;
import com.collegeManagementSystem.learning.entity.SubjectEntity;
import com.collegeManagementSystem.learning.exception.ResourceNotFoundException;
import com.collegeManagementSystem.learning.repository.StudentRepository;
import com.collegeManagementSystem.learning.repository.SubjectRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final ModelMapper mapper;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Transactional(readOnly = true)
    public StudentResponseDTO getStudentById(Long studentId){
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found with Id : "+studentId));
        return entityToResponse(studentEntity);
    }

    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getAllStudents(){
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities.stream()
                .map(studentEntity -> entityToResponse(studentEntity))
                .collect(Collectors.toList());
    }

    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO){
        return entityToResponse(studentRepository.save(requestToEntity(studentRequestDTO)));
    }

    @Transactional
    public StudentResponseDTO enrollSubject(Long studentId, Long subjectId){
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found with id : "+studentId));
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElseThrow(()->new ResourceNotFoundException("Subject not found with id : "+subjectId));
        studentEntity.enrollSubject(subjectEntity);
        return entityToResponse(studentEntity);
    }

    @Transactional
    public StudentResponseDTO unenrollSubject(Long studentId, Long subjectId){
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found with id : "+studentId));
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElseThrow(()->new ResourceNotFoundException("Subject not found with id : "+subjectId));
        studentEntity.unenrollSubject(subjectEntity);
        return entityToResponse(studentEntity);
    }

    private StudentResponseDTO entityToResponse(StudentEntity studentEntity){
        StudentResponseDTO studentResponseDTO = mapper.map(studentEntity, StudentResponseDTO.class);
        Set<Long> subjectIds = studentEntity.getSubjects()
                .stream()
                .map(subject -> subject.getId())
                .collect(Collectors.toSet());
        studentResponseDTO.setSubjectIds(subjectIds);
        return studentResponseDTO;
    }

    private StudentEntity requestToEntity(StudentRequestDTO studentRequestDTO){
        return mapper.map(studentRequestDTO, StudentEntity.class);
    }
}
