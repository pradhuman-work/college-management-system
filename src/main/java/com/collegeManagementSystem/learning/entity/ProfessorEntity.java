package com.collegeManagementSystem.learning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "professor")
public class ProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "professorEntity") //Inverse Side
    private Set<SubjectEntity> subjectEntities = new HashSet<>();

    public void addSubject(SubjectEntity subjectEntity) {
        // 1. add subject to this.subjects
        this.subjectEntities.add(subjectEntity);
        // 2. set the subject's professor to this professor
        subjectEntity.setProfessorEntity(this);
    }

    public void removeSubject(SubjectEntity subjectEntity) {
        // 1. remove subject from this.subjects
        this.subjectEntities.remove(subjectEntity);
        // 2. set the subject's professor to null
        subjectEntity.setProfessorEntity(null);
    }
}
