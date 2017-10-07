package qcryptic.sphin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kyle on 10/6/2017.
 */
@Controller
public class LoginCtrl {

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("loginTitle", "Welcome to Sphin");
        model.addAttribute("loginSubTitle", "Please make an admin account:");
        model.addAttribute("loginButtonText", "Submit");
        model.addAttribute("passwordHint", "Do not forget your password! It cannot be changed.");
        model.addAttribute("showNavButtons", false);
        model.addAttribute("pageName", "welcome");
        return "pages/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginTitle", "Login to Sphin");
        model.addAttribute("loginSubTitle", "Enter you credentials:");
        model.addAttribute("loginButtonText", "Login");
        model.addAttribute("showNavButtons", false);
        model.addAttribute("pageName", "login");
        return "pages/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("loginTitle", "Register for Sphin");
        model.addAttribute("loginSubTitle", "Choose a name and password:");
        model.addAttribute("loginButtonText", "Submit");
        model.addAttribute("passwordHint", "Do not forget your password! It cannot be changed.");
        model.addAttribute("showNavButtons", false);
        model.addAttribute("pageName", "register");
        return "pages/login";
    }

}
