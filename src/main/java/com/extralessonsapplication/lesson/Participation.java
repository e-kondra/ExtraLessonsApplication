package com.extralessonsapplication.lesson;

import com.extralessonsapplication.student.StudentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Participation {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;

    private boolean attended;

}
