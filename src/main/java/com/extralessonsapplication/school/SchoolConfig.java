package com.extralessonsapplication.school;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

//@Configuration
public class SchoolConfig {
    @Bean
    CommandLineRunner commandLineRunner(SchoolRepository repository){
        return args -> {
            SchoolEntity school1 = new SchoolEntity(
                    "International School of Riga",
                    true,
                    "Kalnciema street 118, Riga",
                    "+371 67 624 622");

            SchoolEntity school2 = new SchoolEntity(
                    "King’s College",
                    true,
                    "Turaidas street 1, Piņķi",
                    "+371 25 759 043");

            SchoolEntity school3 = new SchoolEntity(
                    "Exupery",
                    true,
                    "Jaunā street 8, Piņķi",
                    "+371 67 914 834");

            SchoolEntity school4 = new SchoolEntity(
                    "Babite Secondary school",
                    true,
                    "Jūrmalas street 17, Piņķi",
                    "+371 67 914 834");

            SchoolEntity school5 = new SchoolEntity(
                    "Riga secondary school No. 34",
                    true,
                    "Kandavas street 4, Riga",
                    "+371 67 459 090");

            SchoolEntity school6 = new SchoolEntity(
                    "Riga secondary school No. 40",
                    true,
                    "Terbatas street 15-17, Riga",
                    "+371 67 281 662");

            repository.saveAll(
                    List.of(school1, school2, school3, school4, school5, school6));
        };
    }
}
