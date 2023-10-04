package com.extralessonsapplication.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;

@Controller
public class UserController {
    private UserService userService;
    @Autowired // Dependency Injection
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/login")
    public String displayLoginPage(){
        return "login";
    }

    @GetMapping("/user_create")
    public String displayUserCreatingPage(Model model){
        model.addAttribute("roles", Arrays.asList(UserRole.values()));
        return "user";
    }

    @PostMapping("/user_create")
    public String handleUserCreating(UserEntity userEntity, @RequestParam Map<String, String> requestParams){
        try {
            System.out.println(userEntity);
            String studentPersonalCode = requestParams.get("studentPersonalCode");
            //find student by perconalCode if exist
            //userEntity.getStudent(findStudentByPersonalCode); //add it to user
            userEntity.setIsActive(true);
            this.userService.createUser(userEntity);
            return "redirect:/usersList?status=USER_CREATION_SUCCESS";
        } catch (Exception exception){
            return "redirect:/usersList?status=USER_CREATION_FAILED&error" + exception.getMessage();
        }
    }

    @GetMapping("/usersList")
    public String displayUsersList(Model model){
        model.addAttribute("users", this.userService.getAllUsers());
        return "usersList";
    }
}
