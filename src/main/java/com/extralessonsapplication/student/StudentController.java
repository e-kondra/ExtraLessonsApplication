package com.extralessonsapplication.student;

import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.school.SchoolService;
import com.extralessonsapplication.user.UserEntity;
import com.extralessonsapplication.user.UserRole;
import com.extralessonsapplication.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class StudentController {
    private final StudentService studentService;
    private final SchoolService schoolService;
    private final StudentRepository studentRepository;
    private final UserService userService;

    @Autowired
    public StudentController(StudentService studentService, SchoolService schoolService, StudentRepository studentRepository, UserService userService){
        this.studentService = studentService;
        this.schoolService = schoolService;
        this.studentRepository = studentRepository;
        this.userService = userService;
    }
    @GetMapping("/studentsList")
    public String displayStudentsList(Model model,
                                      @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId){
        try {
            UserEntity loggedInUser = this.userService.findUserById(Long.parseLong(loggedInUserId));
            model.addAttribute("students", this.studentService.getAllStudents());
            model.addAttribute("isModerator", this.userService.isUserModerator(loggedInUser));
            model.addAttribute("isTeacher", this.userService.isUserTeacher(loggedInUser));
            return "studentsList";
        } catch (Exception exception){
            return "redirect:/studentsList?status=FAILED" + exception.getMessage();
        }
    }

    @GetMapping("/student_create")
    public String displayStudentCreatingPage(Model model,
                                             @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId,
                                             @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId)
    {
        try {
            UserEntity loggedInUser = this.userService.findUserById(Long.parseLong(loggedInUserId));
            model.addAttribute("isModerator", this.userService.isUserModerator(loggedInUser));
            model.addAttribute("isTeacher", this.userService.isUserTeacher(loggedInUser));
            model.addAttribute("schools", this.schoolService.getAllActiveSchools());
            if(!chosenSchoolId.equals("null")) {
                SchoolEntity currentSchool = this.schoolService.getSchoolById(Long.parseLong(chosenSchoolId));
                model.addAttribute("chosenSchoolId", chosenSchoolId);
                model.addAttribute("currentSchool", currentSchool);
            }
            return "student";
        } catch (Exception exception){
            if(!chosenSchoolId.isBlank()||!chosenSchoolId.isEmpty()) {
                return "redirect:/studentsList/" + chosenSchoolId + "?status=STUDENT_CREATION_FAILED" + exception.getMessage();
            }
            return "redirect:/studentsList?status=STUDENT_CREATION_FAILED" + exception.getMessage();
        }
    }

    @PostMapping("/student_create")
    public String handleStudentCreating(StudentEntity studentEntity,
                                        @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId,
                                        @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId){
        try {
            studentEntity.setIsActive(true);
            UserEntity user = this.userService.findUserById(Long.parseLong(loggedInUserId));
            studentEntity = this.studentService.createStudent(studentEntity);
            System.out.println(studentEntity.getId());
            if(user.getRole()== UserRole.TEACHER){
                System.out.println(user.getRole().toString());
                this.studentService.createParentByTeacher(studentEntity);
            }
            if(!chosenSchoolId.isBlank()||!chosenSchoolId.isEmpty()) {
                return "redirect:/studentsList/" + chosenSchoolId + "?status=STUDENT_WAS_CREATED_SUCCESSFULLY";
            }
            return "redirect:/studentsList?status=STUDENT_WAS_CREATED_SUCCESSFULLY";
        } catch (Exception exception){
            if(!chosenSchoolId.isBlank()||!chosenSchoolId.isEmpty()) {
                return "redirect:/studentsList/" + chosenSchoolId + "?status=STUDENT_CREATION_FAILED" + exception.getMessage();
            }
            return "redirect:/studentsList?status=STUDENT_CREATION_FAILED" + exception.getMessage();
        }
    }

    @GetMapping("/student_update/{id}")
    public String displayStudentUpdatePage(@PathVariable() Long id,
                                           Model model,
                                           @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId,
                                           @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId) {
        try {
            StudentEntity student = this.studentService.findStudentById(id);
            UserEntity loggedInUser = this.userService.findUserById(Long.parseLong(loggedInUserId));
            model.addAttribute("isModerator", this.userService.isUserModerator(loggedInUser));
            model.addAttribute("isTeacher", this.userService.isUserTeacher(loggedInUser));
            model.addAttribute("student", student);
            model.addAttribute("schools", this.schoolService.getAllActiveSchools());
            if(!chosenSchoolId.equals("null")) {
                SchoolEntity currentSchool = this.schoolService.getSchoolById(Long.parseLong(chosenSchoolId));
                model.addAttribute("chosenSchoolId", chosenSchoolId);
                model.addAttribute("currentSchool", currentSchool);
            }
            return "student_update";
        } catch (Exception exception){
            if(!chosenSchoolId.isBlank()||!chosenSchoolId.isEmpty()) {
                return "redirect:/studentsList/"+chosenSchoolId+"?message=STUDENT_UPDATE_FAILED&error=" + exception.getMessage();
            }
            return "redirect:/studentsList?message=STUDENT_UPDATE_FAILED&error=" + exception.getMessage();
        }
    }

    @PostMapping("/student_update/{id}")
    public String handleStudentUpdate(@PathVariable() Long id,
                                      StudentEntity student,
                                      @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId,
                                      @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId){
        try{
            this.studentService.findStudentById(id);
            student.setId(id);
            this.studentService.updateStudent(student);
            UserEntity loggedInUser = this.userService.findUserById(Long.parseLong(loggedInUserId));
            if(this.userService.isUserTeacher(loggedInUser)){
                return "redirect:/studentsList/" + chosenSchoolId + "?status=STUDENT_WAS_UPDATED_SUCCESSFULLY";
            }
            return "redirect:/studentsList?status=STUDENT_WAS_UPDATED_SUCCESSFULLY";
        } catch(Exception e){
            if(!chosenSchoolId.isBlank()||!chosenSchoolId.isEmpty()) {
                return "redirect:/studentsList/"+chosenSchoolId+"?message=STUDENT_UPDATE_FAILED&error=" + e.getMessage();
            }
            return "redirect:/studentsList?status=STUDENT_UPDATING_FAILED&error" + e.getMessage();
        }
    }

    @GetMapping("/studentsList/{id}")
    public String displayStudentsListBySchool(@PathVariable("id") Long schoolId,
                                              Model model,
                                              @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId,
                                              @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId){
        try{
        SchoolEntity school = this.schoolService.getSchoolById(schoolId);
        UserEntity loggedInUser = this.userService.findUserById(Long.parseLong(loggedInUserId));
        model.addAttribute("isModerator", this.userService.isUserModerator(loggedInUser));
        model.addAttribute("isTeacher", this.userService.isUserTeacher(loggedInUser));
        model.addAttribute("students", this.studentService.getAllActiveStudentsBySchool(school));
        if(!chosenSchoolId.equals("null")) {
            model.addAttribute("chosenSchoolId", chosenSchoolId); // for header
            model.addAttribute("currentSchool", this.schoolService.getSchoolById(Long.parseLong(chosenSchoolId)));
        }
        return "studentsList";
        } catch (Exception e){
            return "redirect:/teacher?status=STUDENT_LIST_BY_SCHOOL FAILED";
        }
    }

    @GetMapping("/student_view/{id}")
    public String viewStudent(@PathVariable("id") Long id,
                              Model model,
                              @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId){
        try {
            StudentEntity student = this.studentService.findStudentById(id);
            model.addAttribute("student", student);
            model.addAttribute("schoolId", chosenSchoolId);
            return "student_view";
        } catch (Exception exception){
            return "redirect:/studentsList/"+ chosenSchoolId +"?status=STUDENT_VIEWING_FAILED&error" + exception.getMessage();
        }

    }

}
