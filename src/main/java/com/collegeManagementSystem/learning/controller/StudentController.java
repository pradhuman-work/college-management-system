package com.collegeManagementSystem.learning.controller;

import com.collegeManagementSystem.learning.dto.StudentRequestDTO;
import com.collegeManagementSystem.learning.dto.StudentResponseDTO;
import com.collegeManagementSystem.learning.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentByid(@PathVariable(name = "id") Long studentId){
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.saveStudent(studentRequestDTO));
    }

}
