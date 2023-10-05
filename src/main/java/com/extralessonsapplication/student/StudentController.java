package com.extralessonsapplication.student;

import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.school.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    private final StudentService studentService;
    private SchoolService schoolService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping("/studentsList")
    public String displayUsersList(Model model){
        model.addAttribute("students", this.studentService.getAllUsers());
        return "studentsList";
    }

    @GetMapping("/student_create")
    public String displayStudentCreatingPage(Model model){
        return "student";
    }

    @PostMapping("/student_create")
    public String handleStudentCreating(StudentEntity studentEntity, @RequestParam Map<String, String> requestParams, Model model){
        try {
            System.out.println(studentEntity);
            studentEntity.setIsActive(true);
            this.studentService.createStudent(studentEntity);
            List<SchoolEntity> schools = this.schoolService.getAllSchools();
            model.addAttribute("schools", schools);
            return "redirect:/studentsList?status=STUDENT_CREATION_SUCCESS";
        } catch (Exception exception){
            return "redirect:/studentsList?status=STUDENT_CREATION_FAILED&error" + exception.getMessage();
        }
    }

}
