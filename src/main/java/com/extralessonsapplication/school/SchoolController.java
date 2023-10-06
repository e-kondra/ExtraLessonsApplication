package com.extralessonsapplication.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;

@Controller
public class SchoolController {
    private final SchoolService schoolService;
    @Autowired // Dependency Injection
    public SchoolController(SchoolService schoolService){
        this.schoolService = schoolService;
    }

    @GetMapping("/school_create")
    public String displaySchoolCreatingPage(Model model){
        return "school";
    }

    @PostMapping("/school_create")
    public String handleSchoolCreating(SchoolEntity schoolEntity, @RequestParam Map<String, String> requestParams){
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

}
