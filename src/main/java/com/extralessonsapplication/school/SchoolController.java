package com.extralessonsapplication.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SchoolController {
    private final SchoolService schoolService;
    @Autowired // Dependency Injection
    public SchoolController(SchoolService schoolService){
        this.schoolService = schoolService;
    }

    @GetMapping("/school_create")
    public String displaySchoolCreatingPage(){
        return "school";
    }

    @PostMapping("/school_create")
    public String handleSchoolCreating(SchoolEntity schoolEntity){
        try {
            System.out.println(schoolEntity);
            schoolEntity.setIsActive(true);
            this.schoolService.createSchool(schoolEntity);
            return "redirect:/schoolsList?status=SCHOOL_CREATION_SUCCESS";
        } catch (Exception exception){
            return "redirect:/schoolsList?status=SCHOOL_CREATION_FAILED&error" + exception.getMessage();
        }
    }
    @GetMapping("/schoolsList")
    public String displaySchoolsList(Model model){
        model.addAttribute("schools", this.schoolService.getAllSchools());
        return "schoolsList";
    }

    @GetMapping("/school_update/{id}")
    public String displaySchoolUpdate(@PathVariable() Long id, Model model){
        try {
            SchoolEntity school = this.schoolService.getSchoolById(id);
            model.addAttribute("school", school);
            return "school_update";
        } catch (Exception e){
            return "redirect:/schoolsList?status=SCHOOL_UPDATING_FAILED&error" + e.getMessage();
        }
    }

    @PostMapping("/school_update/{id}")
    public String handleSchoolUpdate(@PathVariable() Long id, SchoolEntity school){
        try{
            this.schoolService.getSchoolById(id);
            school.setId(id);
            this.schoolService.updateSchool(school);
            return "redirect:/schoolsList?status=SCHOOL_UPDATING_SUCCESS";
        } catch(Exception e){
            return "redirect:/schoolsList?status=SCHOOL_UPDATING_FAILED&error" + e.getMessage();
        }
    }

}
