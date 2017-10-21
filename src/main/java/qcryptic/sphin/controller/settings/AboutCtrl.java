package qcryptic.sphin.controller.settings;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import qcryptic.sphin.enums.SettingsEnum;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static qcryptic.sphin.controller.settings.SettingsCtrl.defaultModelAdds;

/**
 * Created by Kyle on 10/21/2017.
 */
@Controller
@RequestMapping("/settings/about")
public class AboutCtrl {

    @Value("${app.version}") private String appVersion;
    @Value("${app.version.release}") private String releaseDate;

    @GetMapping
    public String about(Model model) {
        String configDir = System.getProperty("user.home") + File.separator + "Sphin" + File.separator;
        model = defaultModelAdds(model, SettingsEnum.ABOUT);
        Map<String,String> aboutValues = new LinkedHashMap<>();
        aboutValues.put("Sphin Version", appVersion);
        aboutValues.put("Release Date", releaseDate);
        aboutValues.put("Database Location", configDir + "sphin_database.mv.db");
        aboutValues.put("Properties Location", configDir + "sphin.properties");
        aboutValues.put("Java Version", SystemUtils.JAVA_VERSION);
        aboutValues.put("Java Location", SystemUtils.JAVA_HOME);
        aboutValues.put("OS Name", SystemUtils.OS_NAME);
        aboutValues.put("OS Architecture", SystemUtils.OS_ARCH);
        aboutValues.put("OS Version", SystemUtils.OS_VERSION);
        model.addAttribute("aboutValues", aboutValues);
        return "pages/settings";
    }

}
