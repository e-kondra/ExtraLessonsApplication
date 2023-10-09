package com.extralessonsapplication.participation;

import com.extralessonsapplication.lesson.LessonEntity;
import com.extralessonsapplication.lesson.LessonService;
import com.extralessonsapplication.student.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ParticipationController {

    private final ParticipationService participationService;
    private final LessonService lessonService;
    private final StudentService studentService;

    public ParticipationController(ParticipationService participationService, LessonService lessonService, StudentService studentService) {
        this.participationService = participationService;
        this.lessonService = lessonService;
        this.studentService = studentService;
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
    public String handleParticipationsCreating( @PathVariable() Long lessonId,@RequestParam Map<String, String> requestParams){
        try {
            LessonEntity createdLesson = this.lessonService.getLessonById(lessonId);
            this.participationService.createLessonsParticipations(createdLesson, requestParams, this.studentService.getAllActiveStudentsBySchool(createdLesson.getSchool()));
            return "redirect:/lesson_update/" + lessonId + "?status=PARTICIPATION_CREATING_SUCCESS";
        } catch (Exception exception){
            return "redirect:/lesson_update/" + lessonId + "?status=PARTICIPATION_CREATING_FAILED&error" + exception.getMessage();
        }
    }

}
