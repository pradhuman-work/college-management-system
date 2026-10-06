package com.collegeManagementSystem.learning.repository;

import com.collegeManagementSystem.learning.entity.AdmissionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecordEntity, Long> {
}