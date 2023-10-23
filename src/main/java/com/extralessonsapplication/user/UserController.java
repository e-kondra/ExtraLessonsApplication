package com.extralessonsapplication.user;

import com.extralessonsapplication.school.Counter;
import com.extralessonsapplication.school.SchoolService;
import com.extralessonsapplication.student.StudentEntity;
import com.extralessonsapplication.student.StudentService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
    private final Counter counter = new Counter();
    @Autowired
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
            Cookie cookie = new Cookie("loggedInUserId", user.getId().toString());
            cookie.setMaxAge(10000);
            response.addCookie(cookie);
            switch (user.getRole().toString()){
                case "MODERATOR" -> {return "redirect:/moderator?status=LOGIN_SUCCESS";}
                case "PARENT" -> {return "redirect:/students_participations?status=LOGIN_SUCCESS";}
                case "TEACHER" -> {return "redirect:/teacher?status=LOGIN_SUCCESS";}

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
            String studentPersonalCode = requestParams.get("studentPersonalCode");
            StudentEntity studentEntity = this.studentService.getStudentByPersonalCode(studentPersonalCode);
            userEntity.setStudent(studentEntity);
            userEntity.setIsActive(true);
            this.userService.createUser(userEntity);
            return "redirect:/usersList?status=USER_WAS_CREATED_SUCCESSFULLY";
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
            @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId,
            HttpServletResponse response
    ){
        Cookie cookie = new Cookie("loggedInUserId",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        Cookie cookie2 = new Cookie("chosenSchoolId",null);
        cookie2.setMaxAge(0);
        cookie2.setPath("/");
        response.addCookie(cookie2);
        return "redirect:/?status=LOGOUT_SUCCESSFUL";
    }

    @GetMapping("/user_update/{id}")
    public String displayUserUpdatePage(@PathVariable() Long id, Model model) {
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
            return "redirect:/usersList?status=USER_WAS_UPDATED_SUCCESSFULLY";
        } catch (Exception exception){
            return "redirect:/usersList?status=USER_UPDATE_FAILED&error=" + exception.getMessage();
        }
    }

    @GetMapping("/teacher")
    public String displayTeacherPage(Model model,
                                     @CookieValue(name="chosenSchoolId", required=false) String schoolId) {
        model.addAttribute("counter", this.counter);
        model.addAttribute("schools", this.schoolService.getAllActiveSchools());
        model.addAttribute("chosenSchoolId", schoolId);
        return "teacherMain";
    }

    @GetMapping("/profile")
    public String displayProfile(Model model,
                                 HttpServletRequest request,
                                 @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId,
                                 @RequestParam Map<String, String> requestParams){
       try {
           UserEntity user = this.userService.findUserById(Long.parseLong(loggedInUserId));
           model.addAttribute("userItem", user);
           return "profile";
       }catch (Exception exception){
           return "redirect:/profile?message=PROFILE_FAILED&error=" + exception.getMessage();
       }
    }

    @PostMapping("/profile")
    public String handleProfileUpdating(UserEntity user,
                                        @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId){
        try {
            UserEntity loggedUser = this.userService.findUserById(Long.parseLong(loggedInUserId));
            user.setId(Long.parseLong(loggedInUserId));
            user.setRole(loggedUser.getRole());
            user.setCreatedAt(loggedUser.getCreatedAt());
            user.setStudent(loggedUser.getStudent());
            user.setIsActive(true);
            this.userService.updateUser(user);
            return "redirect:/profile?message=PROFILE_WAS_UPDATED_SUCCESSFULY";
        } catch (Exception exception){
            return "redirect:/profile?message=PROFILE_UPDATING_FAILED&error=" + exception.getMessage();
        }
    }



}
