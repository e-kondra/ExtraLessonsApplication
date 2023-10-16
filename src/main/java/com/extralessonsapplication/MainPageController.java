package com.extralessonsapplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class MainPageController {
    @GetMapping("/")
    public String displayHomePage(){
        return "index";
    }

}
