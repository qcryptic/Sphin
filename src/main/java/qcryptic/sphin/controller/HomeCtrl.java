package qcryptic.sphin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import qcryptic.sphin.service.IUsersSvc;

/**
 * Created by Kyle on 10/1/2017.
 */
@Controller
@RequestMapping("/")
public class HomeCtrl {

    @Autowired
    private IUsersSvc usersSvc;

    @GetMapping
    public ModelAndView method() {
        if (!usersSvc.checkAdminExists())
            return new ModelAndView("redirect:/welcome");
        return new ModelAndView("redirect:/search");
    }

}
