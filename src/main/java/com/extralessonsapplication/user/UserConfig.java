package com.extralessonsapplication.user;

import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.student.StudentEntity;
import com.extralessonsapplication.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository, StudentRepository studentRepository){
        return args -> {
            StudentEntity student1 = studentRepository.findById(1L).orElse(null);
            StudentEntity student2 = studentRepository.findById(2L).orElse(null);
            StudentEntity student3 = studentRepository.findById(3L).orElse(null);
            StudentEntity student4 = studentRepository.findById(4L).orElse(null);
            StudentEntity student5 = studentRepository.findById(5L).orElse(null);
            StudentEntity student6 = studentRepository.findById(6L).orElse(null);
            StudentEntity student7 = studentRepository.findById(7L).orElse(null);
            StudentEntity student8 = studentRepository.findById(8L).orElse(null);
            StudentEntity student9 = studentRepository.findById(9L).orElse(null);
            StudentEntity student10 = studentRepository.findById(10L).orElse(null);
            StudentEntity student11 = studentRepository.findById(11L).orElse(null);
            StudentEntity student12 = studentRepository.findById(12L).orElse(null);
            StudentEntity student13 = studentRepository.findById(13L).orElse(null);


            UserEntity user1 = new UserEntity(
                    1L,
                    "ZinoAdidi",
                    "shegoestech",
                    "Zino",
                    "Adidi",
                    UserRole.TEACHER,
                    "zino@gmail.com",
                    true,
                    null,
                    null
            );

            UserEntity user2 = new UserEntity(
                    2L,
                    "Karlis123",
                    "shegoestech1",
                    "Karlis",
                    "Zars",
                    UserRole.TEACHER,
                    "karlis@gmail.com",
                    true,
                    null,
                    null
            );

            UserEntity user3 = new UserEntity(
                    3L,
                    "moderator",
                    "SGTModerator",
                    "moderator",
                    "moderator",
                    UserRole.MODERATOR,
                    "SGT@gmail.com",
                    true,
                    null,
                    null
            );

            UserEntity user4 = new UserEntity(
                    4L,
                    "122145-12542",
                    "Robotica1A",
                    "Iveta",
                    "Vinogradova",
                    UserRole.PARENT,
                    "ivetaV@gmail.com",
                    true,
                    student1,
                    null,
                    null
            );
            UserEntity user5 = new UserEntity(
                    5L,
                    "763424-16326",
                    "Robotica1B",
                    "Natalja",
                    "Kondratyeva",
                    UserRole.PARENT,
                    "nataliK@gmail.com",
                    true,
                    student2,
                    null,
                    null
            );
            UserEntity user6 = new UserEntity(
                    6L,
                    "452264-22531",
                    "Robotica1C",
                    "Veronika",
                    "Zalygina",
                    UserRole.PARENT,
                    "ververa65@gmail.com",
                    true,
                    student3,
                    null,
                    null
            );
            UserEntity user7 = new UserEntity(
                    7L,
                    "482297-11852",
                    "Robotica1D",
                    "Kristina",
                    "Pakalniskiene",
                    UserRole.PARENT,
                    "kris1@gmail.com",
                    true,
                    student4,
                    null,
                    null
            );
            UserEntity user8 = new UserEntity(
                    8L,
                    "673396-44297",
                    "Robotica1E",
                    "Birute",
                    "Valanciunaite",
                    UserRole.PARENT,
                    "biruteVa@gmail.com",
                    true,
                    student5,
                    null,
                    null
            );
            UserEntity user9 = new UserEntity(
                    9L,
                    "592295-85295",
                    "Robotica1F",
                    "Marta",
                    "Plavina",
                    UserRole.PARENT,
                    "plavina9@gmail.com",
                    true,
                    student6,
                    null,
                    null
            );
            UserEntity user10 = new UserEntity(
                    10L,
                    "840038-63964",
                    "Robotica1G",
                    "Madara",
                    "Cietvira",
                    UserRole.PARENT,
                    "madcietvira@gmail.com",
                    true,
                    student7,
                    null,
                    null
            );
            UserEntity user11 = new UserEntity(
                    11L,
                    "693306-74196",
                    "Robotica1H",
                    "Inese",
                    "Konovalova",
                    UserRole.PARENT,
                    "in_konovalova@gmail.com",
                    true,
                    student8,
                    null,
                    null
            );
            UserEntity user12 = new UserEntity(
                    12L,
                    "503862-96385",
                    "Robotica1I",
                    "Anzelika",
                    "Smiltina",
                    UserRole.PARENT,
                    "smiltina.anzelika@gmail.com",
                    true,
                    student9,
                    null,
                    null
            );
            UserEntity user13 = new UserEntity(
                    13L,
                    "953396-11753",
                    "Robotica1J",
                    "Agnese",
                    "Petkova-Verbiene",
                    UserRole.PARENT,
                    "ap23@gmail.com",
                    true,
                    student10,
                    null,
                    null
            );
            UserEntity user14 = new UserEntity(
                    14L,
                    "740482-75395",
                    "Robotica1K",
                    "Inna",
                    "Nadoleanu",
                    UserRole.PARENT,
                    "inna67@gmail.com",
                    true,
                    student11,
                    null,
                    null
            );
            UserEntity user15 = new UserEntity(
                    15L,
                    "730863-85739",
                    "Robotica1L",
                    "Lina",
                    "Parmionova",
                    UserRole.PARENT,
                    "linlina@gmail.com",
                    true,
                    student12,
                    null,
                    null
            );
            UserEntity user16 = new UserEntity(
                    16L,
                    "591185-85396",
                    "Robotica1M",
                    "Egle",
                    "Jankune",
                    UserRole.PARENT,
                    "linlina@gmail.com",
                    true,
                    student13,
                    null,
                    null
            );

            repository.saveAll(
                    List.of(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11,user12,user13,user14,user15,user16));
        };
    }
}