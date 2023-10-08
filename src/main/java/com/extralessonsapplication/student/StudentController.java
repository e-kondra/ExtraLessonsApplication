package com.extralessonsapplication.student;

import com.extralessonsapplication.school.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class StudentController {
    private final StudentService studentService;
    private final SchoolService schoolService;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentService studentService, SchoolService schoolService, StudentRepository studentRepository){
        this.studentService = studentService;
        this.schoolService = schoolService;
        this.studentRepository = studentRepository;
    }
    @GetMapping("/studentsList")
    public String displayStudentsList(Model model){
        model.addAttribute("students", this.studentService.getAllStudents());
        return "studentsList";
    }

    @GetMapping("/student_create")
    public String displayStudentCreatingPage(Model model){
        model.addAttribute("schools", this.schoolService.getAllActiveSchools());
        return "student";
    }

    @PostMapping("/student_create")
    public String handleStudentCreating(StudentEntity studentEntity, @RequestParam Map<String, String> requestParams){
        try {
            System.out.println(studentEntity);
            System.out.println(requestParams.get("school"));

            studentEntity.setIsActive(true);
            this.studentService.createStudent(studentEntity);
            return "redirect:/studentsList?status=STUDENT_CREATION_SUCCESS";
        } catch (Exception exception){
            return "redirect:/studentsList?status=STUDENT_CREATION_FAILED&error=" + exception.getMessage();
        }
    }

    @GetMapping("/student_update/{id}")
    public String displayStudentUpdatePage(@PathVariable() Long id, Model model){
        try {
            StudentEntity student = this.studentService.findStudentById(id);
            model.addAttribute("student", student);
            model.addAttribute("schools", this.schoolService.getAllActiveSchools());
            return "student_update";
        } catch (Exception exception){
            return "redirect:/studentsList?message=STUDENT_UPDATE_FAILED&error=" + exception.getMessage();
        }
    }

    @PostMapping("/student_update/{id}")
    public String handleStudentUpdate(@PathVariable() Long id, StudentEntity student){
        try{
            this.studentService.findStudentById(id);
            student.setId(id);
            this.studentService.updateStudent(student);
            return "redirect:/studentsList?status=STUDENT_UPDATING_SUCCESS";
        } catch(Exception e){
            return "redirect:/studentsList?status=STUDENT_UPDATING_FAILED&error" + e.getMessage();
        }
    }

}
