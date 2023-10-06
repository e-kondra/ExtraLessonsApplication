package com.extralessonsapplication.student;

import com.extralessonsapplication.school.SchoolEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


@Setter
@Getter
@ToString
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
    private Date contractBegin;
    private Date contractEnd;
    private Boolean isActive;

}
