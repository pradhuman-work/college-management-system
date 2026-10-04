package com.collegeManagementSystem.learning.repository;

import com.collegeManagementSystem.learning.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
