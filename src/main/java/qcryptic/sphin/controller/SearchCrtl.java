package qcryptic.sphin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kyle on 9/26/2017.
 */
@Controller
public class SearchCrtl {

    @RequestMapping(value = {"/", "/search"})
    public String home(Model model) {
        model.addAttribute("info", "Search Page");
        model.addAttribute("pageName", "search");
        return "pages/search";
    }

}
