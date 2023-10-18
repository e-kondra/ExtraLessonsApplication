package com.extralessonsapplication.school;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

public class SchoolConfig {
    public void commandLineRunner(SchoolRepository repository) {
            SchoolEntity school1 = new SchoolEntity(
                    1L,
                    "International School of Riga",
                    true,
                    "Kalnciema street 118, Riga",
                    "+371 67 624 622");

            SchoolEntity school2 = new SchoolEntity(
                    2L,
                    "King’s College",
                    true,
                    "Turaidas street 1, Piņķi",
                    "+371 25 759 043");

            SchoolEntity school3 = new SchoolEntity(
                    3L,
                    "Exupery",
                    true,
                    "Jaunā street 8, Piņķi",
                    "+371 67 914 834");

            SchoolEntity school4 = new SchoolEntity(
                    4L,
                    "Babite Secondary school",
                    true,
                    "Jūrmalas street 17, Piņķi",
                    "+371 67 914 834");

            SchoolEntity school5 = new SchoolEntity(
                    5L,
                    "Riga secondary school No. 34",
                    true,
                    "Kandavas street 4, Riga",
                    "+371 67 459 090");

            SchoolEntity school6 = new SchoolEntity(
                    6L,
                    "Riga secondary school No. 40",
                    true,
                    "Terbatas street 15-17, Riga",
                    "+371 67 281 662");

            repository.saveAll(
                    List.of(school1, school2, school3, school4, school5, school6));
        };
    }

