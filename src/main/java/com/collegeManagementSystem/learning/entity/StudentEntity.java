package com.collegeManagementSystem.learning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="cms_students")
@Getter
@Setter
@NoArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany //Owning Side
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<SubjectEntity> subjects = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="student_professor",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private Set<ProfessorEntity> professors = new HashSet<>();

    public void enrollSubject(SubjectEntity subject){
        // 1. add subject to this.subjects
        this.subjects.add(subject);
        // 2. set the subject's to this student
        subject.getStudents().add(this);
    }

    public void unenrollSubject(SubjectEntity subject){
        // 1. remove subject from this.subjects
        this.subjects.remove(subject);
        // 2. remove this student from the subject's students
        subject.getStudents().remove(this);
    }

    public void addProfessor(ProfessorEntity professorEntity){
        this.professors.add(professorEntity);
        professorEntity.getStudents().add(this);
    }

    public void removeProfessor(ProfessorEntity professorEntity){
        this.professors.remove(professorEntity);
        professorEntity.getStudents().remove(this);
    }

}
