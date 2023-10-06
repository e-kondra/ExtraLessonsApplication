package com.extralessonsapplication.lesson;

import com.extralessonsapplication.lesson.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;

@Controller
public class LessonController {
    private LessonService lessonService;
    @Autowired // Dependency Injection
    public LessonController(LessonService lessonService){
        this.lessonService = lessonService;
    }

    @GetMapping("/lesson_create")
    public String displayLessonCreatingPage(Model model){
        return "lesson";
    }

    @PostMapping("/lesson_create")
    public String handleLessonCreating(LessonEntity lessonEntity, @RequestParam Map<String, String> requestParams){
        try {
            System.out.println(lessonEntity);
            lessonEntity.setIsActive(true);
            this.lessonService.createSchool(lessonEntity);
            return "redirect:/lessonsList?status=SCHOOL_CREATION_SUCCESS";
        } catch (Exception exception){
            return "redirect:/lessonsList?status=SCHOOL_CREATION_FAILED&error" + exception.getMessage();
        }
    }
    @GetMapping("/lessonsList")
    public String displayLessonsList(Model model){
        model.addAttribute("lessons", this.lessonService.getAllLessons());
        return "lessonsList";
    }

}
