package qcryptic.sphin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kyle on 9/28/2017.
 */
@Controller
public class DownloadsCtrl {

    @RequestMapping(value = {"/downloads"})
    public String home(Model model) {
        model.addAttribute("info", "Downloads Page");
        model.addAttribute("pageName", "downloads");
        return "pages/downloads";
    }

}
