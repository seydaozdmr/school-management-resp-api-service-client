package com.schoolmanagement.service.config;

import com.schoolmanagement.service.model.Course;
import com.schoolmanagement.service.repository.CourseRepository;
import com.schoolmanagement.service.repository.StudentRepository;
import com.schoolmanagement.service.model.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase  {

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepository repository, CourseRepository courseRepository){
        return args -> {
            repository.save(new Student("ali","yılmaz", LocalDate.of(1990,11,6)));
            repository.save(new Student("mehmet","kaldırım",LocalDate.of(1988,7,7)));
            System.out.println("2 student loaded");
            courseRepository.save(new Course("Türkçe"));
            courseRepository.save(new Course("Matematik"));
            System.out.println("2 courses loaded");
        };
    }
}
