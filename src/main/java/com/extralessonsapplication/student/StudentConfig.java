package com.extralessonsapplication.student;

import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.school.SchoolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

//@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner studentCommandLineRunner(StudentRepository repository, SchoolRepository schoolRepository){
        return args -> {
            SchoolEntity school1 = schoolRepository.findById(1L).orElse(null);

            StudentEntity student1 = new StudentEntity(
                    "Olga",
                    "Vinogradova",
                    "122145-12542",
                    school1,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student2 = new StudentEntity(
                    "Ekaterina",
                    "Kondratyeva",
                    "763424-16326",
                    school1,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student3 = new StudentEntity(
                    "Valeria",
                    "Zalygina",
                    "452264-22531",
                    school1,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );

            repository.saveAll(
                    List.of(student1,student2,student3));
        };
    }
}
