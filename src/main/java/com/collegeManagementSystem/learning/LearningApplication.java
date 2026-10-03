package com.collegeManagementSystem.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningApplication.class, args);
	}

//    @Bean
//    public CommandLineRunner createProfessor(ProfessorRepository professorRepository,
//                                             SubjectRepository subjectRepository){
//        return args -> {
//            Professor p = new Professor();
//            p.setTitle("Dr. Jha");
//
//            Professor savedProfessor = professorRepository.save(p);
//
//            Subject sub = new Subject();
//            sub.setTitle("English");
//
//            savedProfessor.addSubject(sub);       // links both sides
//            subjectRepository.save(sub);          // saves the subject, with professor_id filled in
//
//            List<Professor> professorList = professorRepository.findAll();
//
//            for(var prof : professorList){
//                System.out.println(prof.getId()+" "+prof.getTitle());
//            }
//            System.out.println("-------SUBJECT-------");
//
//            List<Subject> subjectList = subjectRepository.findAll();
//
//            for(var s : subjectList){
//                System.out.println(s.getId()+" "+s.getTitle());
//            }
//        };
//
//    }

}
