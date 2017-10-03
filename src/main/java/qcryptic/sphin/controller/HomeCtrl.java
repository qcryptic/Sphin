package qcryptic.sphin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Kyle on 10/1/2017.
 */
@Controller
@RequestMapping("/")
public class HomeCtrl {

    @GetMapping
    public ModelAndView method() {
        return new ModelAndView("redirect:/search");
    }

}
