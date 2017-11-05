package qcryptic.sphin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kyle on 9/28/2017.
 */
@Controller
public class HistoryCtrl {

    @RequestMapping(value = {"/history"})
    public String home(Model model) {
        model.addAttribute("showNavButtons", true);
        model.addAttribute("info", "History Page");
        model.addAttribute("pageName", "history");
        return "pages/history";
    }

}
