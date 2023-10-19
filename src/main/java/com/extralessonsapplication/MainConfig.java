package com.extralessonsapplication;

import com.extralessonsapplication.lesson.LessonConfig;
import com.extralessonsapplication.lesson.LessonRepository;
import com.extralessonsapplication.participation.ParticipationConfig;
import com.extralessonsapplication.participation.ParticipationRepository;
import com.extralessonsapplication.school.SchoolConfig;
import com.extralessonsapplication.school.SchoolRepository;
import com.extralessonsapplication.student.StudentConfig;
import com.extralessonsapplication.student.StudentRepository;
import com.extralessonsapplication.user.UserConfig;
import com.extralessonsapplication.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MainConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository userRepository,
                                            StudentRepository studentRepository,
                                            LessonRepository lessonRepository,
                                            SchoolRepository schoolRepository,
                                            ParticipationRepository participationRepository){
        return args -> {
            SchoolConfig schoolConfig = new SchoolConfig();
            schoolConfig.commandLineRunner(schoolRepository);
            StudentConfig studentConfig = new StudentConfig();
            studentConfig.studentCommandLineRunner(studentRepository, schoolRepository);
            UserConfig userConfig = new UserConfig();
            userConfig.userCommandLineRunner(userRepository, studentRepository );
            LessonConfig lessonConfig = new LessonConfig();
            lessonConfig.lessonCommandLineRunner(lessonRepository, userRepository, schoolRepository);
            ParticipationConfig participationConfig = new ParticipationConfig();
            participationConfig.participationCommandLineRunner(participationRepository,
            studentRepository, lessonRepository);
        };
    }
}
