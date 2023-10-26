package com.extralessonsapplication;

import com.extralessonsapplication.lesson.LessonConfig;
import com.extralessonsapplication.lesson.LessonRepository;
import com.extralessonsapplication.message.MessageEntity;
import com.extralessonsapplication.message.MessageRepository;
import com.extralessonsapplication.participation.ParticipationConfig;
import com.extralessonsapplication.participation.ParticipationRepository;
import com.extralessonsapplication.school.SchoolConfig;
import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.school.SchoolRepository;
import com.extralessonsapplication.student.StudentConfig;
import com.extralessonsapplication.student.StudentRepository;
import com.extralessonsapplication.user.UserConfig;
import com.extralessonsapplication.user.UserEntity;
import com.extralessonsapplication.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class MainConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository userRepository,
                                            StudentRepository studentRepository,
                                            LessonRepository lessonRepository,
                                            SchoolRepository schoolRepository,
                                            ParticipationRepository participationRepository,
                                            MessageRepository messageRepository){
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
            UserEntity userParent = userRepository.findById(4L).orElse(null);
            UserEntity userModer = userRepository.findById(3L).orElse(null);
            MessageEntity message = new MessageEntity(
                    1L,
                    LocalDate.of(2023, 10, 27),
                    "Incorrect child name",
                    "Can you correct my daughter‘s name: she is Olga, not Helga (personal code 122145-12542)",
                    null,
                    userParent,
                    userModer,
                    userParent.toString(),
                    userParent.getEmail(),
                    null,
                    false
            );
            messageRepository.saveAll(List.of(message));
        };
    }
}
