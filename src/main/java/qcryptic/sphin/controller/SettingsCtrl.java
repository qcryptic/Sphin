package qcryptic.sphin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kyle on 9/30/2017.
 */
@Controller
public class SettingsCtrl {

    @RequestMapping(value = {"/settings"})
    public String home(Model model) {
        model.addAttribute("info", "Settings Page");
        model.addAttribute("pageName", "settings");
        return "pages/settings";
    }

}
