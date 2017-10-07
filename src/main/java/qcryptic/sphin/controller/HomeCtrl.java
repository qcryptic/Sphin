package qcryptic.sphin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import qcryptic.sphin.service.IGenericSvc;

/**
 * Created by Kyle on 10/1/2017.
 */
@Controller
@RequestMapping("/")
public class HomeCtrl {

    @Autowired
    private IGenericSvc genericSvc;

    @GetMapping
    public ModelAndView method() {
        if (!genericSvc.checkForAdmin())
            return new ModelAndView("redirect:/welcome");
        return new ModelAndView("redirect:/search");
    }

}
