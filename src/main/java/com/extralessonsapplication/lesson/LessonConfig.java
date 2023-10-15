package com.extralessonsapplication.lesson;

import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.school.SchoolRepository;
import com.extralessonsapplication.user.UserEntity;
import com.extralessonsapplication.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class LessonConfig {
    @Bean
    CommandLineRunner lessonCommandLineRunner(LessonRepository repository, UserRepository userRepository,
                                              SchoolRepository schoolRepository) {
        return args -> {
            UserEntity teacher1 = userRepository.findById(1L).orElse(null);
            SchoolEntity school1 = schoolRepository.findById(1L).orElse(null);
            UserEntity teacher2 = userRepository.findById(2L).orElse(null);
            SchoolEntity school2 = schoolRepository.findById(2L).orElse(null);


            LessonEntity lesson1 = new LessonEntity(
                    1L,
                    LocalDate.of(2023, 9, 1),
                    "Meet the Robots: An Introduction to Robotics",
                    "In our first lesson, we'll meet the amazing world of robots and 3D pens. Get ready " +
                            "to discover what makes them so special and fun!",
                    teacher1,
                    school1,
                    false
            );
            LessonEntity lesson2 = new LessonEntity(
                    2L,
                    LocalDate.of(2023, 9, 8),
                    "Building Our First Robot: The Robot Kit Adventure",
                    "Are you ready to become a robot builder? We'll start with robot kits and use 3D pens" +
                            " to make our first robot friends.",
                    teacher1,
                    school1,
                    false
            );
            LessonEntity lesson3 = new LessonEntity(
                    3L,
                    LocalDate.of(2023, 9, 15),
                    "Robot Friends: How Robots Communicate",
                    "RRobots have their own way of talking to each other. We'll explore how robots communicate " +
                            "and create art with 3D pens",
                    teacher1,
                    school1,
                    false
            );
            LessonEntity lesson4 = new LessonEntity(
                    4L,
                    LocalDate.of(2023, 9, 22),
                    "Robot Detectives: Sensors and How They Work",
                    "Have you ever wondered how robots can see and sense their surroundings? We'll learn about" +
                            " sensors and how they work.",
                    teacher1,
                    school1,
                    false
            );
            LessonEntity lesson5 = new LessonEntity(
                    5L,
                    LocalDate.of(2023, 9, 29),
                    "Robot Art: Creating with 3D Pens and Robots",
                    "Unleash your creativity! Robots and 3D pens will help us create amazing sculptures and " +
                            "art. Let's make colorful masterpieces together.",
                    teacher1,
                    school1,
                    false
            );
            LessonEntity lesson6 = new LessonEntity(
                    6L,
                    LocalDate.of(2023, 10, 6),
                    "Fun with Coding: Teaching Robots New Tricks",
                    "Coding is like teaching robots to dance or tell jokes. We'll have fun learning how to" +
                            " give our robots new tricks and customize our robots with 3D pen accessories.",
                    teacher1,
                    school1,
                    false
            );
            LessonEntity lesson7 = new LessonEntity(
                    7L,
                    LocalDate.of(2023, 10, 13),
                    "Robot Games: Challenges and Competitions",
                    "It's time for some friendly robot competitions and games. Let's see whose robot can " +
                            "complete challenges and win, and we'll even 3D print trophies for the winners.",
                    teacher1,
                    school1,
                    true
            );
            LessonEntity lesson8 = new LessonEntity(
                    8L,
                    LocalDate.of(2023, 10, 20),
                    "Robot Story Time: Learning About Robot History",
                    "Join us for a journey through the history of robots. We'll learn about famous robots " +
                            "from the past and how they've inspired us",
                    teacher1,
                    school1,
                    true
            );
            LessonEntity lesson9 = new LessonEntity(
                    9L,
                    LocalDate.of(2023, 10, 27),
                    "Robot Party: Celebrating What We've Learned with 3D Pen Creations",
                    "Let's celebrate the wonderful world of robotics and 3D pens with a robot-themed party." +
                            " We'll showcase what we've learned and display our 3D pen creations for everyone to enjoy!",
                    teacher1,
                    school1,
                    true
            );


            LessonEntity lesson101 = new LessonEntity(
                    101L,
                    LocalDate.of(2023, 9, 1),
                    "Meet the Robots: An Introduction to Robotics",
                    "In our first lesson, we'll meet the amazing world of robots and 3D pens. Get ready " +
                            "to discover what makes them so special and fun!",
                    teacher2,
                    school2,
                    false
            );
            LessonEntity lesson102 = new LessonEntity(
                    102L,
                    LocalDate.of(2023, 9, 8),
                    "Building Our First Robot: The Robot Kit Adventure",
                    "Are you ready to become a robot builder? We'll start with robot kits and use 3D pens" +
                            " to make our first robot friends.",
                    teacher2,
                    school2,
                    false
            );
            LessonEntity lesson103 = new LessonEntity(
                    103L,
                    LocalDate.of(2023, 9, 15),
                    "Robot Friends: How Robots Communicate",
                    "RRobots have their own way of talking to each other. We'll explore how robots communicate " +
                            "and create art with 3D pens",
                    teacher2,
                    school2,
                    false
            );
            LessonEntity lesson104 = new LessonEntity(
                    104L,
                    LocalDate.of(2023, 9, 22),
                    "Robot Detectives: Sensors and How They Work",
                    "Have you ever wondered how robots can see and sense their surroundings? We'll learn about" +
                            " sensors and how they work.",
                    teacher2,
                    school2,
                    false
            );
            LessonEntity lesson105 = new LessonEntity(
                    105L,
                    LocalDate.of(2023, 9, 29),
                    "Robot Art: Creating with 3D Pens and Robots",
                    "Unleash your creativity! Robots and 3D pens will help us create amazing sculptures and " +
                            "art. Let's make colorful masterpieces together.",
                    teacher2,
                    school2,
                    false
            );
            LessonEntity lesson106 = new LessonEntity(
                    106L,
                    LocalDate.of(2023, 10, 6),
                    "Fun with Coding: Teaching Robots New Tricks",
                    "Coding is like teaching robots to dance or tell jokes. We'll have fun learning how to" +
                            " give our robots new tricks and customize our robots with 3D pen accessories.",
                    teacher2,
                    school2,
                    false
            );
            LessonEntity lesson107 = new LessonEntity(
                    107L,
                    LocalDate.of(2023, 10, 13),
                    "Robot Games: Challenges and Competitions",
                    "It's time for some friendly robot competitions and games. Let's see whose robot can " +
                            "complete challenges and win, and we'll even 3D print trophies for the winners.",
                    teacher2,
                    school2,
                    true
            );
            LessonEntity lesson108 = new LessonEntity(
                    108L,
                    LocalDate.of(2023, 10, 20),
                    "Robot Story Time: Learning About Robot History",
                    "Join us for a journey through the history of robots. We'll learn about famous robots " +
                            "from the past and how they've inspired us",
                    teacher2,
                    school2,
                    true
            );
            LessonEntity lesson109 = new LessonEntity(
                    109L,
                    LocalDate.of(2023, 10, 27),
                    "Robot Party: Celebrating What We've Learned with 3D Pen Creations",
                    "Let's celebrate the wonderful world of robotics and 3D pens with a robot-themed party." +
                            " We'll showcase what we've learned and display our 3D pen creations for everyone to enjoy!",
                    teacher2,
                    school2,
                    true
            );





            repository.saveAll(
                    List.of(lesson1,lesson2,lesson3,lesson4,lesson5,lesson6,lesson7,lesson8,lesson9,lesson101,lesson102,
                            lesson103,lesson104,lesson105,lesson106,lesson107,lesson108,lesson109));


    };

}
}