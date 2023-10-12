package com.extralessonsapplication.user;

import com.extralessonsapplication.student.StudentEntity;
import com.extralessonsapplication.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

//Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository, StudentRepository studentRepository){
        return args -> {
            StudentEntity student1 = studentRepository.findByPersonalCodeEquals("122145-12542");

            UserEntity user1 = new UserEntity(
                    "ZinoAdidi",
                    "shegoestech",
                    "Zino",
                    "Adidi",
                    UserRole.TEACHER,
                    "zino@gmail.com",
                    true,
                    student1,
                    null,
                    null
            );

            repository.saveAll(
                    List.of(user1));
        };
    }
}