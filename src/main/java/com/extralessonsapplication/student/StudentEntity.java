package com.extralessonsapplication.student;

import com.extralessonsapplication.school.SchoolEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastName;
    private String personalCode;
    @ManyToOne
    private SchoolEntity school;
    private Timestamp contractBegin;
    private Timestamp contractEnd;
    boolean isActive;
}
