package com.github.maximovj.libhubtec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/email")
    public String email(Model model) {
        model.addAttribute("code", "85059");
        model.addAttribute("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
        model.addAttribute("name", "User Demo");
        return "email";
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("message", "This is the welcome page.");
        return "welcome";
    }

}
