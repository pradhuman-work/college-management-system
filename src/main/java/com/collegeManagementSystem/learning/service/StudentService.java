package com.collegeManagementSystem.learning.service;

import com.collegeManagementSystem.learning.dto.StudentRequestDTO;
import com.collegeManagementSystem.learning.dto.StudentResponseDTO;
import com.collegeManagementSystem.learning.entity.StudentEntity;
import com.collegeManagementSystem.learning.exception.ResourceNotFoundException;
import com.collegeManagementSystem.learning.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final ModelMapper mapper;
    private final StudentRepository studentRepository;

    public StudentService(ModelMapper mapper, StudentRepository studentRepository) {
        this.mapper = mapper;
        this.studentRepository = studentRepository;
    }

    public StudentResponseDTO getStudentById(Long studentId){
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student not found with Id : "+studentId));
        return entityToResponse(studentEntity);
    }

    public List<StudentResponseDTO> getAllStudents(){
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities.stream()
                .map(studentEntity -> entityToResponse(studentEntity))
                .collect(Collectors.toList());
    }

    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO){
        return entityToResponse(studentRepository.save(requestToEntity(studentRequestDTO)));
    }

    private StudentResponseDTO entityToResponse(StudentEntity studentEntity){
        return mapper.map(studentEntity, StudentResponseDTO.class);
    }

    private StudentEntity requestToEntity(StudentRequestDTO studentRequestDTO){
        return mapper.map(studentRequestDTO, StudentEntity.class);
    }
}
