package com.collegeManagementSystem.learning.repository;

import com.collegeManagementSystem.learning.entity.ProfessorEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    @Override
    @EntityGraph(attributePaths = {"students", "students.admissionRecord"})
    List<ProfessorEntity> findAll();
}
