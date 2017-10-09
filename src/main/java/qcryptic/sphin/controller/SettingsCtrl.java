package qcryptic.sphin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import qcryptic.sphin.enums.SettingsEnum;
import qcryptic.sphin.service.IUsersSvc;
import qcryptic.sphin.utils.SphinUtils;
import qcryptic.sphin.vo.DbResponseVo;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by Kyle on 9/30/2017.
 */
@Controller
@RequestMapping("/settings")
public class SettingsCtrl {

    @Autowired
    private IUsersSvc usersSvc;

    @Value("${server.port}") private int port;
    @Value("${server.context-path}") private String path;
    @Value("${server.address}") private String address;

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
        model.addAttribute("port", port);
        model.addAttribute("address", address);
        model.addAttribute("baseUrl", path);
        return "pages/settings";
    }

    @ResponseBody
    @PostMapping("/network/update")
    public DbResponseVo updateNetwork(@RequestParam("port") Integer serverPort, @RequestParam("ip") String bindIp, @RequestParam("url") String urlBase) {
        final Pattern ipRegex = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        if (serverPort > 65535 || serverPort < 1) 
            return new DbResponseVo(false, "Port number must be between 1-65535");
        if (!ipRegex.matcher(bindIp).matches())
            return new DbResponseVo(false, "Bind IP must be a valid IPv4 address");
        if (!urlBase.startsWith("/"))
            return new DbResponseVo(false, "URL Base must start with '/'");
        if (!urlBase.matches("/[a-zA-Z0-9/]*"))
            return new DbResponseVo(false, "Invalid URL Base, alphanumeric and forward slashes only");
        HashMap<String,String> newSettings = new HashMap<>();
        newSettings.put("server.port", serverPort.toString());
        newSettings.put("server.address", bindIp);
        newSettings.put("server.context-path", urlBase);
        SphinUtils.updateProperties(newSettings);
        return new DbResponseVo(true, "Updated - restart required to take effect");
    }

    @GetMapping("/users")
    public String users(Model model) {
        model = defaultModelAdds(model, SettingsEnum.USERS);
        return "pages/settings";
    }

    @ResponseBody
    @PostMapping("/users/makeInvite")
    public DbResponseVo makeInvite(@RequestParam("rank") String rankName) {
        return usersSvc.generateInviteLink(rankName, 24);
    }

}
