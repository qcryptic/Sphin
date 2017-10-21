package qcryptic.sphin.controller.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qcryptic.sphin.enums.SettingsEnum;
import qcryptic.sphin.utils.SphinUtils;
import qcryptic.sphin.vo.DbResponseVo;

import java.util.HashMap;
import java.util.regex.Pattern;

import static qcryptic.sphin.controller.settings.SettingsCtrl.defaultModelAdds;

/**
 * Created by Kyle on 10/21/2017.
 */
@Controller
@RequestMapping("/settings/network")
public class NetworkCtrl {

    @Value("${server.port}") private int port;
    @Value("${server.context-path}") private String path;
    @Value("${server.address}") private String address;

    @GetMapping
    public String network(Model model) {
        model = defaultModelAdds(model, SettingsEnum.NETWORK);
        model.addAttribute("port", port);
        model.addAttribute("address", address);
        model.addAttribute("baseUrl", path);
        return "pages/settings";
    }

    @ResponseBody
    @PostMapping("/update")
    public DbResponseVo updateNetwork(@RequestParam("port") Integer serverPort, @RequestParam("ip") String bindIp, @RequestParam("url") String urlBase) {
        final Pattern ipRegex = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        if (serverPort > 65535 || serverPort < 1)
            return new DbResponseVo(false, "Port number must be between 1-65535");
        if (!ipRegex.matcher(bindIp).matches())
            return new DbResponseVo(false, "Bind IP must be a valid IPv4 address");
        if (!urlBase.startsWith("/"))
            return new DbResponseVo(false, "URL Base must start with '/'");
        if (!urlBase.matches("/[a-zA-Z0-9]*"))
            return new DbResponseVo(false, "Invalid URL Base, alphanumeric and forward slashes only");
        HashMap<String,String> newSettings = new HashMap<>();
        newSettings.put("server.port", serverPort.toString());
        newSettings.put("server.address", bindIp);
        newSettings.put("server.context-path", urlBase);
        SphinUtils.updateProperties(newSettings);
        return new DbResponseVo(true, "Updated - restart required to take effect");
    }

}
