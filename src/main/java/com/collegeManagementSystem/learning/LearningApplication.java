package com.collegeManagementSystem.learning;

import com.collegeManagementSystem.learning.entity.ProfessorEntity;
import com.collegeManagementSystem.learning.entity.SubjectEntity;
import com.collegeManagementSystem.learning.repository.ProfessorRepository;
import com.collegeManagementSystem.learning.repository.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningApplication.class, args);
	}

//    @Bean
//    public CommandLineRunner createProfessor(ProfessorRepository professorRepository,
//                                             SubjectRepository subjectRepository){
//        return args -> {
//            ProfessorEntity p = new ProfessorEntity();
//            p.setTitle("Dr. Jha");
//
//            ProfessorEntity savedProfessor = professorRepository.save(p);
//
//            SubjectEntity sub = new SubjectEntity();
//            sub.setTitle("English");
//
//            savedProfessor.addSubject(sub);       // links both sides
//            subjectRepository.save(sub);          // saves the subject, with professor_id filled in
//
//            List<ProfessorEntity> professorList = professorRepository.findAll();
//
//            for(var prof : professorList){
//                System.out.println(prof.getId()+" "+prof.getTitle());
//            }
//            System.out.println("-------SUBJECT-------");
//
//            List<SubjectEntity> subjectList = subjectRepository.findAll();
//
//            for(var s : subjectList){
//                System.out.println(s.getId()+" "+s.getTitle());
//            }
//        };
//
//    }

}
