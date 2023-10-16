package com.extralessonsapplication.participation;

import com.extralessonsapplication.lesson.LessonEntity;
import com.extralessonsapplication.lesson.LessonService;
import com.extralessonsapplication.school.Counter;
import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.school.SchoolService;
import com.extralessonsapplication.student.StudentEntity;
import com.extralessonsapplication.student.StudentService;
import com.extralessonsapplication.user.UserEntity;
import com.extralessonsapplication.user.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class ParticipationController {

    private final ParticipationService participationService;
    private final LessonService lessonService;
    private final StudentService studentService;
    private final SchoolService schoolService;
    private final UserService userService;
    private Map<Integer,Integer> monthSequence = new LinkedHashMap<>();
    private Counter counter = new Counter();


    public ParticipationController(ParticipationService participationService, LessonService lessonService, StudentService studentService, SchoolService schoolService, UserService userService) {
        this.participationService = participationService;
        this.lessonService = lessonService;
        this.studentService = studentService;
        this.schoolService = schoolService;
        this.userService = userService;
        this.fillMonthSequence();
    }

    private void fillMonthSequence(){
        this.monthSequence.put(9, 2023);
        this.monthSequence.put(10, 2023);
        this.monthSequence.put(11, 2023);
        this.monthSequence.put(12, 2023);
        this.monthSequence.put(1, 2024);
        this.monthSequence.put(2, 2024);
        this.monthSequence.put(3, 2024);
        this.monthSequence.put(4, 2024);
        this.monthSequence.put(5, 2024);
        this.monthSequence.put(6, 2024);
    }

    @GetMapping("/participations_create/{lessonId}")
    public String displayParticipationsCreating(@PathVariable() Long lessonId, Model model){
        try {
            LessonEntity createdLesson = this.lessonService.getLessonById(lessonId);
            model.addAttribute("lesson", createdLesson);
            model.addAttribute("students", this.studentService.getAllActiveStudentsBySchool(createdLesson.getSchool()));
            return "school_students";
        } catch (Exception exception){
            return "redirect:/lesson_update/" + lessonId + "?status=PARTICIPATION_CREATING_FAILED&error" + exception.getMessage();
        }
    }

    @PostMapping("/participations_create/{lessonId}")
    public String handleParticipationsCreating( @PathVariable() Long lessonId,
                                                @RequestParam Map<String, String> requestParams){
        try {
            LessonEntity createdLesson = this.lessonService.getLessonById(lessonId);
            this.participationService.createLessonsParticipations(createdLesson, requestParams, this.studentService.getAllActiveStudentsBySchool(createdLesson.getSchool()));
            return "redirect:/lesson_update/" + lessonId + "?status=PARTICIPATION_CREATING_SUCCESS";
        } catch (Exception exception){
            return "redirect:/lesson_update/" + lessonId + "?status=PARTICIPATION_CREATING_FAILED&error" + exception.getMessage();
        }
    }

    @GetMapping("/calculation")
    public String displayCalculation(Model model,
                                     @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId,
                                     @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId){
        try{
            SchoolEntity currentSchool = this.schoolService.getSchoolById(Long.parseLong(chosenSchoolId));
            UserEntity loggedInUser = this.userService.findUserById(Long.parseLong(loggedInUserId));
            model.addAttribute("isModerator", this.userService.isUserModerator(loggedInUser));
            model.addAttribute("isTeacher", this.userService.isUserTeacher(loggedInUser));
            model.addAttribute("studentsAmount", this.participationService.getStudentsPayments(currentSchool, this.monthSequence));
            model.addAttribute("chosenSchoolId", chosenSchoolId); // for header
            model.addAttribute("months", this.participationService.getMonthSequence(this.monthSequence));
            model.addAttribute("monthsSeq", this.participationService.getMonthSequenceInt(this.monthSequence));
            model.addAttribute("yearsSeq", this.participationService.getYearSequenceInt(this.monthSequence));
            return "calculation";
        } catch (Exception exception){
            return "redirect:/calculation?status=CALCULATION_FAILED&error" + exception.getMessage();
        }
    }

    @GetMapping("/invoice/{id}/{month}/{year}")
    public String displayInvoice(@PathVariable("id") Long studentId,
                                 @PathVariable() int month,
                                 @PathVariable() int year,
                                 Model model){
        try {
            StudentEntity student = this.studentService.findStudentById(studentId);
            model.addAttribute("counter", this.counter);
            model.addAttribute("participations", this.participationService.getStudentMonthParticipation(student, month, year));
            model.addAttribute("amount", this.participationService.getSummaryForStudentByMonth(student, month, year));
            model.addAttribute("student", student);
            model.addAttribute("parent", this.userService.findUserByStudent(student));
            model.addAttribute("monthYear", Month.of(month) + " " + year);
            return "invoice";
        }catch (Exception exception){
            return "redirect:/calculation?status=INVOICE_FAILED&error" + exception.getMessage();
        }
    }
    @GetMapping("/invoice")
    public String displayInvoice2(){
        return "invoice";
    }

}
