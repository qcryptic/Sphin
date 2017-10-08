package qcryptic.sphin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import qcryptic.sphin.enums.SettingsEnum;

/**
 * Created by Kyle on 9/30/2017.
 */
@Controller
@RequestMapping("/settings")
public class SettingsCtrl {

    private Model defaultModelAdds(Model curModel, SettingsEnum type) {
        curModel.addAttribute("showNavButtons", true);
        curModel.addAttribute("pageName", "settings");
        curModel.addAttribute("settingsTitle", type.getTitle());
        curModel.addAttribute("settingsSubTitle", type.getSubtitle());
        curModel.addAttribute("settingsShowSave", type.isShowSaveBtn());
        curModel.addAttribute("settingType", type.name().toLowerCase());
        return curModel;
    }

    @GetMapping("/about")
    public String about(Model model) {
        model = defaultModelAdds(model, SettingsEnum.ABOUT);
        return "pages/settings";
    }

    @GetMapping("/network")
    public String network(Model model) {
        model = defaultModelAdds(model, SettingsEnum.NETWORK);
        return "pages/settings";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model = defaultModelAdds(model, SettingsEnum.USERS);
        return "pages/settings";
    }

}
