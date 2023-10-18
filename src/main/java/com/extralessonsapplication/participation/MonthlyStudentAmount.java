package com.extralessonsapplication.participation;

import com.extralessonsapplication.student.StudentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class MonthlyStudentAmount {
    private Long id;
    private StudentEntity student;
    private ArrayList<Integer> amounts;  // from 9 to 12, from 1 to 6 months
}
