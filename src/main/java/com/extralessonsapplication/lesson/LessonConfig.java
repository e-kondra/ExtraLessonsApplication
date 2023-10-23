package com.extralessonsapplication.lesson;

import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.school.SchoolRepository;
import com.extralessonsapplication.user.UserEntity;
import com.extralessonsapplication.user.UserRepository;


import java.time.LocalDate;
import java.util.List;


public class LessonConfig {

    public void lessonCommandLineRunner(LessonRepository repository, UserRepository userRepository,
                                              SchoolRepository schoolRepository) {
            UserEntity teacher1 = userRepository.findById(1L).orElse(null);
            SchoolEntity school1 = schoolRepository.findById(1L).orElse(null);
            UserEntity teacher2 = userRepository.findById(2L).orElse(null);
            SchoolEntity school2 = schoolRepository.findById(2L).orElse(null);


            LessonEntity lesson1 = new LessonEntity(
                    1L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 9, 8)),
                    "Meet the Robots: An introduction to robotics",
                    "In our first lesson,we will meet the amazing world of robots and 3D pens. " +
                            "Get ready to discover what makes them so special and fun!",
                    teacher1,
                    school1,
                    true
            );
            LessonEntity lesson2 = new LessonEntity(
                   2L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 9, 15)),
                    "Building Our First Robot: The Robot Kit Adventure",
                    "Are you ready to become a robot builder? We'll start with robot kits and use 3D pens" +
                            " to make our first robot friends.",
                    teacher1,
                    school1,
                    true
            );

            LessonEntity lesson3 = new LessonEntity(
                    3L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 9, 22)),
                    "Robot Friends: How Robots Communicate",
                    "Robots have their own way of talking to each other. We'll explore how robots communicate " +
                            "and create art with 3D pens",

                    teacher1,
                    school1,
                    true
            );
            LessonEntity lesson4 = new LessonEntity(
                    4L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 9, 29)),
                    "Robot Detectives: Sensors and How They Work",
                    "Have you ever wondered how robots can see and sense their surroundings? We'll learn about" +
                            " sensors and how they work.",

                    teacher1,
                    school1,
                    true
            );
            LessonEntity lesson5 = new LessonEntity(
                    5L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 10, 6)),
                    "Robot Art: Creating with 3D Pens and Robots",
                    "Unleash your creativity! Robots and 3D pens will help us create amazing sculptures and " +
                            "art. Let's make colorful masterpieces together.",

                    teacher2,
                    school1,
                    true
            );
            LessonEntity lesson6 = new LessonEntity(
                    6L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 10, 13)),
                    "Fun with Coding: Teaching Robots New Tricks",
                    "Coding is like teaching robots to dance or tell jokes. We'll have fun learning how to" +
                            " give our robots new tricks and customize our robots with 3D pen accessories.",

                    teacher1,
                    school1,
                    true
            );
            LessonEntity lesson7 = new LessonEntity(
                    7L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 10, 20)),
                    "Robot Games: Challenges and Competitions",
                    "It's time for some friendly robot competitions and games. Let's see whose robot can " +
                            "complete challenges and win, and we'll even 3D print trophies for the winners.",
                    teacher1,
                    school1,
                    true
            );
//            LessonEntity lesson8 = new LessonEntity(
//                    8L,
//                    java.sql.Date.valueOf(LocalDate.of(2023, 10, 27)),
//                    "Robot Story Time: Learning About Robot History",
//                    "Join us for a journey through the history of robots. We'll learn about famous robots " +
//                            "from the past and how they've inspired us",
//                    teacher1,
//                    school1,
//                    true
//            );


            LessonEntity lesson101 = new LessonEntity(
                    9L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 9, 8)),
                    "Meet the Robots: An introduction to robotics",
                    "In our first lesson,we will meet the amazing world of robots and 3D pens. " +
                            "Get ready to discover what makes them so special and fun!",
                    teacher2,
                    school2,
                    true
            );
            LessonEntity lesson102 = new LessonEntity(
                    10L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 9, 15)),
                    "Building Our First Robot: The Robot Kit Adventure",
                    "Are you ready to become a robot builder? We'll start with robot kits and use 3D pens" +
                            " to make our first robot friends.",
                    teacher2,
                    school2,
                    true
            );
            LessonEntity lesson103 = new LessonEntity(
                    11L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 9, 22)),
                    "Robot Friends: How Robots Communicate",
                    "Robots have their own way of talking to each other. We'll explore how robots communicate " +
                            "and create art with 3D pens",
                    teacher2,
                    school2,
                    true
            );
            LessonEntity lesson104 = new LessonEntity(
                    12L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 9, 29)),
                    "Robot Detectives: Sensors and How They Work",
                    "Have you ever wondered how robots can see and sense their surroundings? We'll learn about" +
                            " sensors and how they work.",
                    teacher2,
                    school2,
                    true
            );
            LessonEntity lesson105 = new LessonEntity(
                    13L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 10, 6)),
                    "Robot Art: Creating with 3D Pens and Robots",
                    "Unleash your creativity! Robots and 3D pens will help us create amazing sculptures and " +
                            "art. Let's make colorful masterpieces together.",
                    teacher2,
                    school2,
                    true
            );
            LessonEntity lesson106 = new LessonEntity(
                    14L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 10, 13)),
                    "Fun with Coding: Teaching Robots New Tricks",
                    "Coding is like teaching robots to dance or tell jokes. We'll have fun learning how to" +
                            " give our robots new tricks and customize our robots with 3D pen accessories.",

                    teacher2,
                    school2,
                    true
            );
            LessonEntity lesson107 = new LessonEntity(
                    15L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 10, 20)),
                    "Robot Games: Challenges and Competitions",
                    "It's time for some friendly robot competitions and games. Let's see whose robot can " +
                            "complete challenges and win, and we'll even 3D print trophies for the winners.",
                    teacher2,
                    school2,
                    true
            );
            LessonEntity lesson108 = new LessonEntity(
                    16L,
                    java.sql.Date.valueOf(LocalDate.of(2023, 10, 27)),
                    "Robot Story Time: Learning About Robot History",
                    "Join us for a journey through the history of robots. We'll learn about famous robots " +
                            "from the past and how they've inspired us",
                    teacher2,
                    school2,
                    true
            );

            repository.saveAll(
                    List.of(lesson1, lesson2,lesson3,lesson4,lesson5,lesson6,lesson7,lesson101,lesson102,
                            lesson103,lesson104,lesson105,lesson106,lesson107,lesson108));

    };

}
