package com.extralessonsapplication.lesson;

import com.extralessonsapplication.participation.ParticipationService;
import com.extralessonsapplication.school.SchoolService;
import com.extralessonsapplication.student.StudentService;
import com.extralessonsapplication.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;

@Controller
public class LessonController {

    private final SchoolService schoolService;
    private final UserService userService;
    private final StudentService studentService;
    private final ParticipationService participationService;
    private LessonService lessonService;
    @Autowired // Dependency Injection
    public LessonController(SchoolService schoolService, LessonService lessonService, UserService userService, StudentService studentService, ParticipationService participationService){
        this.schoolService = schoolService;
        this.lessonService = lessonService;
        this.userService = userService;
        this.studentService = studentService;
        this.participationService = participationService;
    }

    @GetMapping("/lesson_create")
    public String displayLessonCreatingPage(Model model){

        model.addAttribute("schools", this.schoolService.getAllActiveSchools());
        model.addAttribute("teachers", this.userService.getAllActiveTeachers());
        model.addAttribute("NowDate", LocalDate.now());
        return "lesson";
    }

    @PostMapping("/lesson_create")
    public String handleLessonCreating(LessonEntity lessonEntity, @RequestParam Map<String, String> requestParams, Model model){
        try {
            lessonEntity.setIsActive(true);
            LessonEntity createdLesson = this.lessonService.createLessonObj(lessonEntity);
            System.out.println(createdLesson);
            //this.lessonService.createLesson(lessonEntity);
            return "redirect:/participations_create/" + lessonEntity.getId() + "?status=LESSON_CREATION_SUCCESS";
        } catch (Exception exception){
            return "redirect:/lessonsList?status=LESSON_CREATION_FAILED&error" + exception.getMessage();
        }
    }
    @GetMapping("/participations_create/{lessonId}")
    public String displayParticipationsCreating(@PathVariable() Long lessonId, Model model){
        try {
            LessonEntity createdLesson = this.lessonService.getLessonById(lessonId);
            model.addAttribute("lesson", createdLesson);
            model.addAttribute("students", this.studentService.getAllActiveStudentsBySchool(createdLesson.getSchool()));
            return "school_students";
        } catch (Exception exception){
            return "redirect:/lesson_update/?status=STUDENTS_PARTICIPATION_FAILED&error" + exception.getMessage();
        }
    }

    @PostMapping("/participations_create/{lessonId}")
    public String handleParticipationsCreating( @PathVariable() Long lessonId,@RequestParam Map<String, String> requestParams){
        try {
            LessonEntity createdLesson = this.lessonService.getLessonById(lessonId);
            this.participationService.createLessonsParticipations(createdLesson, requestParams, this.studentService.getAllActiveStudentsBySchool(createdLesson.getSchool()));
            return "redirect:/lesson_update/?status=STUDENTS_PARTICIPATION_SUCCESS";
        } catch (Exception exception){
            return "redirect:/lesson_update/?status=STUDENTS_PARTICIPATION_FAILED&error" + exception.getMessage();
        }
    }


    @GetMapping("/lessonsList")
    public String displayLessonsList(Model model){
        model.addAttribute("lessons", this.lessonService.getAllLessons());
        return "lessonsList";
    }

}
