package com.extralessonsapplication.student;

import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.school.SchoolRepository;


import java.time.LocalDate;
import java.util.List;

public class StudentConfig {


    public void studentCommandLineRunner(StudentRepository repository, SchoolRepository schoolRepository)
{
            SchoolEntity school1 = schoolRepository.findById(1L).orElse(null);
            SchoolEntity school2 = schoolRepository.findById(2L).orElse(null);

            StudentEntity student1 = new StudentEntity(
                    1L,
                    "Helga",
                    "Vinogradova",
                    "122145-12542",
                    school1,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student2 = new StudentEntity(
                    2L,
                    "Ekaterina",
                    "Kondratyeva",
                    "763424-16326",
                    school1,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student3 = new StudentEntity(
                    3L,
                    "Valeria",
                    "Zalygina",
                    "452264-22531",
                    school1,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student4 = new StudentEntity(
                    4L,
                    "Ramune",
                    "Pakalniskiene",
                    "482297-11852",
                    school1,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student5 = new StudentEntity(
                    5L,
                    "Rasa",
                    "Valanciunaite",
                    "673396-44297",
                    school1,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student6 = new StudentEntity(
                    6L,
                    "Baiba",
                    "Plavina",
                    "592295-85295",
                    school1,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student7 = new StudentEntity(
                    7L,
                    "Kitija",
                    "Cietvira",
                    "840038-63964",
                    school1,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student8 = new StudentEntity(
                    8L,
                    "Nadezda",
                    "Konovalova",
                    "693306-74196",
                    school1,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student9 = new StudentEntity(
                    9L,
                    "Agnese",
                    "Smiltina",
                    "503862-96385",
                    school2,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student10 = new StudentEntity(
                    10L,
                    "Aleksandra",
                    "Petkova-Verbiene",
                    "953396-11753",
                    school2,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student11 = new StudentEntity(
                    11L,
                    "Florina-Grațiela",
                    "Nadoleanu",
                    "740482-75395",
                    school2,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student12 = new StudentEntity(
                    12L,
                    "Karolina",
                    "Parmionova",
                    "730863-85739",
                    school2,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );
            StudentEntity student13 = new StudentEntity(
                    13L,
                    "Vilma",
                    "Jankune",
                    "591185-85396",
                    school2,
                    LocalDate.of(2023, 9, 1),
                    LocalDate.of(2024, 6, 15),
                    true
            );

            repository.saveAll(
                    List.of(student1,student2,student3,student4,student5,student6,student7,student8,student9,student10,student11,student12,student13));

        };
    }

