package com.extralessonsapplication.user;

import com.extralessonsapplication.school.SchoolService;
import com.extralessonsapplication.student.StudentEntity;
import com.extralessonsapplication.student.StudentService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


@Controller
public class UserController {
    private final UserService userService;
    private final StudentService studentService;
    private final SchoolService schoolService;
    @Autowired // Dependency Injection
    public UserController(UserService userService, StudentService studentService, SchoolService schoolService){
        this.userService = userService;
        this.studentService = studentService;
        this.schoolService = schoolService;
    }
    @GetMapping("/login")
    public String displayLoginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String handleUserLogin(UserLoginRequest userLoginRequest, HttpServletResponse response){
        try{
            UserEntity user = this.userService.verifyUser(userLoginRequest.getUsername(), userLoginRequest.getPassword());
            if (user == null) throw new Exception("Username or password is not correct");
            //create cookie and save user id to the cookie/ session
            Cookie cookie = new Cookie("loggedInUserId", user.getId().toString());
            cookie.setMaxAge(10000);
            response.addCookie(cookie);
            switch (user.getRole().toString()){
                case "MODERATOR" -> {return "redirect:/moderator?status=LOGIN_SUCCESS";}
                case "PARENT" -> {return "redirect:/lessonsList?status=LOGIN_SUCCESS";}
                case "TEACHER" -> {return "redirect:/lessonsList?status=LOGIN_SUCCESS";}
            }
            return "redirect:/?status=LOGIN_FAILED";
        } catch(Exception e){
            return "redirect:/login?status=LOGIN_FAILED&error=" + e.getMessage();
        }
    }

    @GetMapping("/moderator")
    public String displayModeratorMainPage() {
        return "/moderatorMainPage";
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
            StudentEntity studentEntity = this.studentService.getStudentByPersonalCode(studentPersonalCode);
            userEntity.setStudent(studentEntity);
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

    @GetMapping("/logout")
    public String handleLogout(
            @CookieValue(value = "loggedInUserId", defaultValue = "") String UserId,
            HttpServletResponse response
    ){
        Cookie cookie = new Cookie("loggedInUserId",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/?status=LOGOUT_SUCCESSFUL";
    }

    @GetMapping("/user_update/{id}")
    public String displayUserUpdatePage(@PathVariable() Long id, Model model) {
        System.out.println("id=" + id);
        try {
            UserEntity user = this.userService.findUserById(id);
            model.addAttribute("userItem", user);
            model.addAttribute("student", user.getStudent());
            model.addAttribute("roles", UserRole.values());
            return "user_update";
        } catch (Exception exception) {
            return "redirect:/usersList?message=USER_UPDATE_FAILED&error=" + exception.getMessage();
        }
    }

    @PostMapping("/user_update/{id}")
    public String handleUserUpdate(@PathVariable() Long id, UserEntity user,@RequestParam Map<String, String> requestParams){
        try {
            this.userService.findUserById(id);
            user.setId(id);
            String studentPersonalCode = requestParams.get("studentPersonalCode");
            user.setStudent(this.studentService.getStudentByPersonalCode(studentPersonalCode));
            this.userService.updateUser(user);
            return "redirect:/usersList?message=USER_UPDATE_SUCCESS";
        } catch (Exception exception){
            return "redirect:/usersList?message=USER_UPDATE_FAILED&error=" + exception.getMessage();
        }
    }

    @GetMapping("teacher")
    public String displayTeacherPage(Model model){
        model.addAttribute("schools", this.schoolService.getAllActiveSchools());
        return "teacherMain";
    }

}
