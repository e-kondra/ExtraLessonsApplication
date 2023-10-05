package com.extralessonsapplication.school;

import com.extralessonsapplication.student.StudentEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SchoolEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Boolean isActive;
    @OneToMany (mappedBy = "school")
    private List<StudentEntity> students;

}
