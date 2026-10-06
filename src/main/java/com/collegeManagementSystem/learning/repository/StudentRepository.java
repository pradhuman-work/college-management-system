package com.collegeManagementSystem.learning.repository;

import com.collegeManagementSystem.learning.entity.StudentEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    @Override
    @EntityGraph(attributePaths = {"subjects", "professors", "admissionRecord"})
    List<StudentEntity> findAll();
}
