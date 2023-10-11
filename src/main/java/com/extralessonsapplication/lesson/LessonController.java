package com.extralessonsapplication.lesson;

import com.extralessonsapplication.participation.ParticipationService;
import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.school.SchoolService;
import com.extralessonsapplication.student.StudentEntity;
import com.extralessonsapplication.student.StudentService;
import com.extralessonsapplication.user.UserEntity;
import com.extralessonsapplication.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
    public String displayLessonCreatingPage(Model model,
                                            @CookieValue(name = "loggedInUserId", defaultValue = "null") String userId,
                                            @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId){
        try {
            UserEntity currentUser = this.userService.findUserById(Long.parseLong(userId));
            model.addAttribute("schools", this.schoolService.getAllActiveSchools());
            model.addAttribute("teachers", this.userService.getAllActiveTeachers());
            model.addAttribute("NowDate", LocalDate.now());
            model.addAttribute("isModerator", this.userService.isUserModerator(currentUser));
            model.addAttribute("isTeacher", this.userService.isUserTeacher(currentUser));
            model.addAttribute("teacher", currentUser);
            if(!chosenSchoolId.equals("null")) {
                model.addAttribute("chosenSchoolId", chosenSchoolId); // for header
                model.addAttribute("currentSchool", this.schoolService.getSchoolById(Long.parseLong(chosenSchoolId)));
            }
            return "lesson";
        }catch (Exception e){
            return "lesson";
        }
    }

    @PostMapping("/lesson_create")
    public String handleLessonCreating(LessonEntity lessonEntity,
                                       @RequestParam Map<String, String> requestParams,
                                       Model model){
        try {
            lessonEntity.setIsActive(true);
            LessonEntity createdLesson = this.lessonService.createLessonObj(lessonEntity);
            System.out.println(createdLesson);
            return "redirect:/participations_create/" + lessonEntity.getId() + "?status=LESSON_CREATION_SUCCESS";
        } catch (Exception exception){
            return "redirect:/lessonsList?status=LESSON_CREATION_FAILED&error" + exception.getMessage();
        }
    }

    @GetMapping("/lessonsList")
    public String displayLessonsList(Model model){
        model.addAttribute("lessons", this.lessonService.getAllLessons());
        return "lessonsList";
    }

    @GetMapping("/lesson_update/{id}")
    public String displayLessonUpdate(@PathVariable("id") Long lessonId,
                                      Model model,
                                      @CookieValue(name = "loggedInUserId", defaultValue = "null") String userId,
                                      @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId){
        try{
            LessonEntity lesson = this.lessonService.getLessonById(lessonId);
            UserEntity loggedInUser = this.userService.findUserById(Long.parseLong(userId));

            model.addAttribute("lesson", lesson);
            model.addAttribute("schools", this.schoolService.getAllActiveSchools());
            model.addAttribute("teachers", this.userService.getAllActiveTeachers());
            model.addAttribute("participations", this.participationService.getParticipationsByLesson(lesson));
            model.addAttribute("isModerator", this.userService.isUserModerator(loggedInUser));
            model.addAttribute("isTeacher", this.userService.isUserTeacher(loggedInUser));
            model.addAttribute("teacher", loggedInUser);
            if(!chosenSchoolId.equals("null")) {
                model.addAttribute("chosenSchoolId", chosenSchoolId); // for header
                model.addAttribute("currentSchool", this.schoolService.getSchoolById(Long.parseLong(chosenSchoolId)));
            }
            return "lesson_update";
        } catch (Exception e){
            return "redirect:/lessonsList?status=LESSON_UPDATING_FAILED&error" + e.getMessage();
        }
    }

    @PostMapping("/lesson_update/{id}")
    public String handleLessonUpdate(@PathVariable("id") Long lessonId,
                                     LessonEntity updatedLesson,
                                     @RequestParam Map<String, String> requestParams,
                                     @CookieValue(name = "loggedInUserId", defaultValue = "null") String userId,
                                     @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId){
        try{
            System.out.println(requestParams);
            this.lessonService.updateLesson(updatedLesson);
            this.participationService.updateLessonsParticipations(updatedLesson,
                    requestParams, this.participationService.getParticipationsByLesson(updatedLesson));
            UserEntity currentUser = this.userService.findUserById(Long.parseLong(userId));
            if(this.userService.isUserModerator(currentUser))
                return "redirect:/lessonsList?status=LESSON_UPDATING_SUCCESS";
            if(this.userService.isUserTeacher(currentUser))
                return "redirect:/lessonsList/" + chosenSchoolId + "?status=LESSON_UPDATING_SUCCESS";
            return "redirect:/lessonsList?status=LESSON_UPDATING_FAILED&error";
        } catch (Exception e) {
            return "redirect:/lessonsList?status=LESSON_UPDATING_FAILED&error" + e.getMessage();
        }
    }

    @GetMapping("/lessonsList/{id}")
    public String displayLessonsListBySchool(@PathVariable("id") Long schoolId, HttpServletResponse response, HttpServletRequest request, Model model){
        try {
            SchoolEntity school = this.schoolService.getSchoolById(schoolId);
            Cookie cookie = new Cookie("chosenSchoolId",schoolId.toString());
            cookie.setMaxAge(10000);
            cookie.setPath("/");
            response.addCookie(cookie);

            model.addAttribute("chosenSchoolId", schoolId);
            model.addAttribute("school", school);
            for (LessonEntity st :this.lessonService.getLessonsBySchool(school)){
                System.out.println(st);
            }
            model.addAttribute("lessons", this.lessonService.getLessonsBySchool(school));
            return "lessonsListBySchool";
        }catch (Exception e){
            return "redirect:/lessonsList/" +schoolId+ "?status=LESSON_UPDATING_FAILED&error" + e.getMessage();
        }
    }

    @GetMapping("/lesson_view/{id}")
    public String viewLesson(@PathVariable("id") Long lessonId,
                             Model model,
                             @CookieValue(name = "chosenSchoolId") String chosenSchoolId){
        try {
            LessonEntity lesson = this.lessonService.getLessonById(lessonId);
            model.addAttribute("lesson", lesson);
            model.addAttribute("participations", this.participationService.getParticipationsByLesson(lesson));
            model.addAttribute("schoolId", Long.parseLong(chosenSchoolId));
            if(!chosenSchoolId.equals("null")) {
                model.addAttribute("chosenSchoolId", chosenSchoolId); // for header
                model.addAttribute("currentSchool", this.schoolService.getSchoolById(Long.parseLong(chosenSchoolId)));
            }
            return "lesson_view";
        }catch (Exception exception){
            return "redirect:/lessonsList?status=LESSON_VIEWING_FAILED&error" + exception.getMessage();
        }
    }

}
