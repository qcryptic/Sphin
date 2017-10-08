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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String missingParamterHandler(Exception exception, Model model) {
        model.addAttribute("errorText", exception.getMessage());
        return "error/400";
    }

    @GetMapping
    public String home(@RequestParam(value="type") String settingType, Model model) {
        settingType = settingType.toUpperCase();
        SettingsEnum type = null;
        try {
            type =  SettingsEnum.valueOf(settingType);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorText", "Invalid type parameter");
            return "error/400";
        }
        model.addAttribute("settingsTitle", type.getTitle());
        model.addAttribute("settingsSubTitle", type.getSubtitle());
        model.addAttribute("settingsShowSave", type.isShowSaveBtn());
        model.addAttribute("settingType", settingType.toLowerCase());
        model.addAttribute("showNavButtons", true);
        model.addAttribute("info", "Settings Page");
        model.addAttribute("pageName", "settings");
        return "pages/settings";
    }

}
