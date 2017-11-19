package qcryptic.sphin.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qcryptic.sphin.dao.IConnectionsDao;
import qcryptic.sphin.enums.ConnectionTypes;
import qcryptic.sphin.enums.Connections;
import qcryptic.sphin.service.ISearchSvc;
import qcryptic.sphin.vo.DbResponseVo;
import qcryptic.sphin.vo.SearchResultVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 9/26/2017.
 */
@Controller
@RequestMapping("/search")
public class SearchCrtl {

    @Autowired
    private ISearchSvc searchSvc;

    @Autowired
    private IConnectionsDao connectionsDao;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("showNavButtons", true);
        model.addAttribute("pageName", "search");
        model.addAttribute("searchType", "movie");
        return "pages/search";
    }

    @ResponseBody
    @PostMapping("/movie")
    public List<SearchResultVo> searchMovie(@RequestParam("query") String query) {
        ArrayList<SearchResultVo> movies = new ArrayList<>();
        Connections activeMovieManager = connectionsDao.getActiveConnection(ConnectionTypes.MOVIE);
        switch (activeMovieManager) {
            case RADARR:
                return  searchSvc.getMoviesRadarr(query);
            default:
                movies.add(new SearchResultVo("badrequest", "Movie manager required - set one up in connections settings"));
                return movies;
        }
    }

    @ResponseBody
    @PostMapping("/tv")
    public List<SearchResultVo> searchTv(@RequestParam("query") String query) {
        ArrayList<SearchResultVo> shows = new ArrayList<>();
        Connections activeTvManager = connectionsDao.getActiveConnection(ConnectionTypes.TV);
        switch (activeTvManager) {
            case SONARR:
                return searchSvc.getTvSonarr(query);
            default:
                shows.add(new SearchResultVo("badrequest", "Movie manager required - set one up in connections settings"));
                return shows;
        }
    }

    @ResponseBody
    @PostMapping("/addMovie")
    public DbResponseVo addMovie(@RequestParam("tmdbId") Integer tmdbId, @RequestParam("title") String title, @RequestParam("profileId") Integer profileId,
                                 @RequestParam("titleSlug") String titleSlug, @RequestParam("path") String path, @RequestParam("images") JSONArray images) {
        Connections activeMovieManager = connectionsDao.getActiveConnection(ConnectionTypes.MOVIE);
        switch (activeMovieManager) {
            case RADARR:
                return searchSvc.addMovieRadarr(tmdbId, title, titleSlug, profileId, path, images);
            default:
                return new DbResponseVo(false, "Error - no valid movie manager found, set one up in the connection settings");
        }
    }

    @ResponseBody
    @PostMapping("/addTv")
    public DbResponseVo addTv(@RequestParam("tvdbId") Integer tvdbId) {
        Connections activeMovieManager = connectionsDao.getActiveConnection(ConnectionTypes.TV);
        switch (activeMovieManager) {
            case SONARR:
                return searchSvc.addTvSonarr(tvdbId);
            default:
                return new DbResponseVo(false, "Error - no valid TV manager found, set one up in the connection settings");
        }
    }

}
