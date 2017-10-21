package qcryptic.sphin.controller.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import qcryptic.sphin.enums.SettingsEnum;

import static qcryptic.sphin.controller.settings.SettingsCtrl.defaultModelAdds;

/**
 * Created by Kyle on 10/21/2017.
 */
@Controller
@RequestMapping("/settings/connections")
public class ConnectionsCtrl {

    @Value("${server.port}") private int port;
    @Value("${server.context-path}") private String path;
    @Value("${server.address}") private String address;

    @GetMapping
    public String connections(Model model) {
        model = defaultModelAdds(model, SettingsEnum.CONNECTIONS);
        model.addAttribute("port", port);
        model.addAttribute("address", address);
        model.addAttribute("baseUrl", path);
        return "pages/settings";
    }

}
