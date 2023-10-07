package com.extralessonsapplication.lesson;
import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.student.StudentEntity;
import com.extralessonsapplication.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LessonEntity {
    @Id @GeneratedValue
    private Long id;
    private Timestamp date;
    private String title;
    private String comment;
    @ManyToOne
    private UserEntity teacher;
    @ManyToOne
    private SchoolEntity school;
    private Boolean isActive;
    @OneToMany(mappedBy = "lesson")
    Set<Participation> participations;

}
