package com.extralessonsapplication.participation;

import com.extralessonsapplication.lesson.LessonEntity;
import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.student.StudentEntity;
import com.extralessonsapplication.student.StudentService;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final StudentService studentService;
    private final int attendedClassPrice = 9;
    private final int notAttendedClassPrice = 4;

    public ParticipationService(ParticipationRepository participationRepository, StudentService studentService) {
        this.participationRepository = participationRepository;
        this.studentService = studentService;
    }

    public void createLessonsParticipations(LessonEntity lesson,
                                            Map<String, String> participatedStudents,
                                            ArrayList<StudentEntity> students) {
        for(StudentEntity student: students){
            Participation participation = new Participation(
                    student,
                    lesson,
                    participatedStudents.containsKey(student.getId().toString()));
            this.participationRepository.save(participation);
        }
    }

    public ArrayList<Participation> getParticipationsByLesson(LessonEntity lesson){
        return this.participationRepository.findAllByLessonEquals(lesson);
    }

    public void updateLessonsParticipations(LessonEntity updatedLesson,
                                            Map<String, String> editedParticipations,
                                            ArrayList<Participation> participations) {
        for(Participation part: participations){
            part.setAttended(editedParticipations.containsKey(part.getId().toString()));
            this.participationRepository.save(part);
        }
    }
    public ArrayList<Participation> getStudentMonthParticipation(StudentEntity student, int _month, int year){
        YearMonth month = YearMonth.of(year, _month);
        Date startDate = Date.valueOf(month.atDay(1));
        Date endDate = Date.valueOf(month.atEndOfMonth());

        ArrayList<Participation> studentParticipations = this.participationRepository.findAllByStudent(student);
        ArrayList<Participation> monthPart = new ArrayList<>();

        for(Participation part: studentParticipations ){
            if((part.getLesson().getDate().compareTo(endDate) <= 0)
                    && (part.getLesson().getDate().compareTo(startDate) >= 0)){
                monthPart.add(part);
            }
        }
        return monthPart;
    }

    public int calcMonthlyPayment(ArrayList<Participation> monthPart){
        int sum = 0;
        for(Participation part: monthPart ){
            if(part.isAttended()==true) {
                sum += this.attendedClassPrice;
            } else {
                sum += this.notAttendedClassPrice;
            }
        }
        return sum;
    }

    public int getSummaryForStudentByMonth(StudentEntity student, int month, int year){
        ArrayList<Participation> monthPart = getStudentMonthParticipation(student, month, year);
        return calcMonthlyPayment(monthPart);
    }

    public ArrayList<MonthlyStudentAmount> getStudentsPayments(SchoolEntity currentSchool, Map<Integer,Integer> monthSequence){
        try {
            ArrayList<MonthlyStudentAmount> studentAmounts = new ArrayList<>();
            ArrayList<StudentEntity> students = this.studentService.getAllActiveStudentsBySchool(currentSchool);
            for (StudentEntity student: students){

                MonthlyStudentAmount monthlyStudentAmount = new MonthlyStudentAmount();
                monthlyStudentAmount.setStudent(student);

                ArrayList<Integer> amounts = new ArrayList<>();
                for(Map.Entry<Integer ,Integer> entry: monthSequence.entrySet()) {
                    amounts.add(this.getSummaryForStudentByMonth(student, entry.getKey(), entry.getValue()));
                }
                monthlyStudentAmount.setAmounts(amounts);
                studentAmounts.add(monthlyStudentAmount);
            }
            return studentAmounts;

        }catch (Exception exception){
            return null;
        }
    }

    public ArrayList<String> getMonthSequence(Map<Integer, Integer> monthSequence) {
        ArrayList<String> monthsYears = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry: monthSequence.entrySet()) {
            monthsYears.add(Month.of(entry.getKey()) + " " + entry.getValue().toString());
        }
        return monthsYears;
    }

    public ArrayList<Integer> getMonthSequenceInt(Map<Integer, Integer> monthSequence) {
        ArrayList<Integer> monthsInt = new ArrayList<>();
        for(Integer key: monthSequence.keySet()){
            monthsInt.add(key);
        }
        return monthsInt;
    }

    public ArrayList<Integer> getYearSequenceInt(Map<Integer, Integer> monthSequence) {
        ArrayList<Integer> yearsInt = new ArrayList<>();
        for (Integer value : monthSequence.values()) {
            yearsInt.add(value);
        }
        return yearsInt;
    }

    public ArrayList<MonthlyStudentParticipations> getMonthlyStudentParticipations(
            StudentEntity student,
            LinkedHashMap<Integer, Integer> monthYear){
        ArrayList<MonthlyStudentParticipations> monthlyStudentParticipations = new ArrayList<>();
        for (Map.Entry<Integer, Integer> my : monthYear.entrySet()){
            if(this.getSummaryForStudentByMonth(student, my.getKey(), my.getValue()) > 0){
                MonthlyStudentParticipations msp = new MonthlyStudentParticipations();
                msp.setMonth(my.getKey());
                msp.setYear(my.getValue());
                msp.setMonthStr(Month.of(my.getKey()).toString());
                msp.setAmount(this.getSummaryForStudentByMonth(student, my.getKey(), my.getValue()));
                ArrayList monthPart = this.getStudentMonthParticipation(student, my.getKey(), my.getValue());
                msp.setParticipations(monthPart);
                monthlyStudentParticipations.add(msp);
            }
        }
        Collections.reverse(monthlyStudentParticipations);
        return monthlyStudentParticipations;
    }
}
