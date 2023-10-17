package com.extralessonsapplication.participation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@Data
public class MonthlyStudentParticipations {
    private Long id;
    private int month;
    private String monthStr;
    private int year;
    ArrayList<Participation> participations;
    private int amount;

}
