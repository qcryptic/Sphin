package qcryptic.sphin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qcryptic.sphin.service.IUsersSvc;
import qcryptic.sphin.vo.DbResponseVo;

/**
 * Created by Kyle on 10/6/2017.
 */
@Controller
public class LoginCtrl {

    @Autowired
    private IUsersSvc usersSvc;

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("loginTitle", "Welcome to Sphin");
        model.addAttribute("loginSubTitle", "Please make an admin account:");
        model.addAttribute("loginButtonText", "Submit");
        model.addAttribute("loginType", "admin");
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
        model.addAttribute("loginType", "login");
        model.addAttribute("showNavButtons", false);
        model.addAttribute("pageName", "login");
        return "pages/login";
    }

    @GetMapping("/register")
    public String register(@RequestParam(value="key", required = true) String uid, Model model) {
        model.addAttribute("loginTitle", "Register for Sphin");
        model.addAttribute("loginSubTitle", "Choose a name and password:");
        model.addAttribute("loginButtonText", "Submit");
        model.addAttribute("loginType", "register");
        model.addAttribute("passwordHint", "Do not forget your password! It cannot be changed.");
        model.addAttribute("showNavButtons", false);
        model.addAttribute("pageName", "register");
        return "pages/login";
    }

    @ResponseBody
    @PostMapping("/welcome/addAdmin")
    public DbResponseVo addAdmin(@RequestParam("user") String user, @RequestParam("pass") String pass) {
        return usersSvc.addAdmin(user, pass);
    }

    @ResponseBody
    @PostMapping("/login")
    public DbResponseVo doLogin(@RequestParam("user") String user, @RequestParam("pass") String pass) {
        return usersSvc.login(user, pass);
    }

}
