package qcryptic.sphin.controller.settings;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import qcryptic.sphin.SphinApplication;
import qcryptic.sphin.enums.SettingsEnum;
import qcryptic.sphin.service.IUsersSvc;
import qcryptic.sphin.utils.SphinUtils;
import qcryptic.sphin.vo.DbResponseVo;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Kyle on 9/30/2017.
 */
@Controller
@RequestMapping("/settings")
public class SettingsCtrl {

    @Autowired
    private RestartEndpoint restartEndpoint;

    public static Model defaultModelAdds(Model curModel, SettingsEnum type) {
        curModel.addAttribute("showNavButtons", true);
        curModel.addAttribute("pageName", "settings");
        curModel.addAttribute("settingsTitle", type.getTitle());
        curModel.addAttribute("settingsSubTitle", type.getSubtitle());
        curModel.addAttribute("settingType", type.name().toLowerCase());
        return curModel;
    }

    @ResponseBody
    @PostMapping("/restart")
    public void restartApp() {
        Thread restartThread = new Thread(() -> restartEndpoint.restart());
        restartThread.setDaemon(false);
        restartThread.start();
    }

}
