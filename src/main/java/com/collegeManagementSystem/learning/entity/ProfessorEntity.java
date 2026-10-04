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

    @OneToMany(mappedBy = "professor") //Inverse Side
    private Set<SubjectEntity> subjects = new HashSet<>();

    public void addSubject(SubjectEntity subjectEntity) {
        if(subjectEntity.getProfessor()!=null){
            subjectEntity.getProfessor().getSubjects().remove(subjectEntity);
        }
        // 1. add subject to this.subjects
        this.subjects.add(subjectEntity);
        // 2. set the subject's professor to this professor
        subjectEntity.setProfessor(this);
    }

    public void removeSubject(SubjectEntity subjectEntity) {
        // 1. remove subject from this.subjects
        this.subjects.remove(subjectEntity);
        // 2. set the subject's professor to null
        subjectEntity.setProfessor(null);
    }
}
