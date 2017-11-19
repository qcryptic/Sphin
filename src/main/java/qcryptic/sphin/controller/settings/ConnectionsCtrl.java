package qcryptic.sphin.controller.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qcryptic.sphin.enums.ConnectionTypes;
import qcryptic.sphin.enums.Connections;
import qcryptic.sphin.enums.SettingsEnum;
import qcryptic.sphin.service.IConnectionsSvc;
import qcryptic.sphin.vo.DbResponseVo;
import qcryptic.sphin.vo.connections.DarrVo;

import static qcryptic.sphin.controller.settings.SettingsCtrl.defaultModelAdds;

/**
 * Created by Kyle on 10/21/2017.
 */
@Controller
@RequestMapping("/settings/connections")
public class ConnectionsCtrl {

    @Autowired
    private IConnectionsSvc connectionsSvc;

    @GetMapping
    public String connections(Model model) {
        model = defaultModelAdds(model, SettingsEnum.CONNECTIONS);
        String activeMovieManager = connectionsSvc.getActiveConnection(ConnectionTypes.MOVIE).getName();
        String activeTvManager = connectionsSvc.getActiveConnection(ConnectionTypes.TV).getName();
        String activeDownloader = connectionsSvc.getActiveConnection(ConnectionTypes.DOWNLOADER).getName();
        model.addAttribute("movieManager", ("none".equals(activeMovieManager) ? "Select..." : activeMovieManager));
        model.addAttribute("tvManager", ("none".equals(activeTvManager) ? "Select..." : activeTvManager));
        model.addAttribute("downloader", ("none".equals(activeDownloader) ? "Select..." : activeDownloader));
        model.addAttribute("radarr", connectionsSvc.getDarrInfo(Connections.RADARR));
        model.addAttribute("sonarr", connectionsSvc.getDarrInfo(Connections.SONARR));
        return "pages/settings";
    }

    @ResponseBody
    @PostMapping("/testDarr")
    public DbResponseVo testDarr(@RequestParam("url") String url, @RequestParam("api") String api) {
        return connectionsSvc.testDarr(url, api);
    }

    @ResponseBody
    @PostMapping("/update-radarr")
    public DbResponseVo updateRadarr(@RequestParam("url") String url, @RequestParam("api") String api, @RequestParam("pathName") String pathName,
                                     @RequestParam("profileId") Integer profileId, @RequestParam("pathId") Integer pathId) {
        return connectionsSvc.updateConnection(Connections.RADARR, ConnectionTypes.MOVIE, new DarrVo(url, api, pathId, profileId, pathName));
    }

    @ResponseBody
    @PostMapping("/update-sonarr")
    public DbResponseVo updateSonarr(@RequestParam("url") String url, @RequestParam("api") String api, @RequestParam("pathName") String pathName,
                                     @RequestParam("profileId") Integer profileId, @RequestParam("pathId") Integer pathId) {
        return connectionsSvc.updateConnection(Connections.SONARR, ConnectionTypes.TV, new DarrVo(url, api, pathId, profileId, pathName));
    }

}