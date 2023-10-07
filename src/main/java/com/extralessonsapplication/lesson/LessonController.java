package com.extralessonsapplication.lesson;

import com.extralessonsapplication.school.SchoolService;
import com.extralessonsapplication.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Controller
public class LessonController {

    private final SchoolService schoolService;
    private final UserService userService;
    private LessonService lessonService;
    @Autowired // Dependency Injection
    public LessonController(SchoolService schoolService, LessonService lessonService, UserService userService){
        this.schoolService = schoolService;
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @GetMapping("/lesson_create")
    public String displayLessonCreatingPage(Model model){
        model.addAttribute("schools", this.schoolService.getAllActiveSchools());
        model.addAttribute("teachers", this.userService.getAllActiveTeachers());
        model.addAttribute("NowDate", LocalDate.now());
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
