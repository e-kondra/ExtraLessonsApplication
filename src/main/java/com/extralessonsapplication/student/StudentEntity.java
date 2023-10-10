package com.extralessonsapplication.student;

import com.extralessonsapplication.participation.Participation;
import com.extralessonsapplication.school.SchoolEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String lastName;
    private String personalCode;
    @ManyToOne
    private SchoolEntity school;
    private LocalDate contractBegin;
    private LocalDate contractEnd;
    private Boolean isActive = false;
    @OneToMany(mappedBy = "student")
    Set<Participation> participations;

    public StudentEntity(String name, String lastName, String personalCode, SchoolEntity school, LocalDate contractBegin, LocalDate contractEnd, Boolean isActive) {
        this.name = name;
        this.lastName = lastName;
        this.personalCode = personalCode;
        this.school = school;
        this.contractBegin = contractBegin;
        this.contractEnd = contractEnd;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return name + " " + lastName;
    }

}