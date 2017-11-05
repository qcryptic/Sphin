package qcryptic.sphin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import qcryptic.sphin.service.ISearchSvc;
import qcryptic.sphin.vo.DbResponseVo;
import qcryptic.sphin.vo.MovieSearchResultVo;
import qcryptic.sphin.vo.TvSearchResultVo;

import java.util.List;

/**
 * Created by Kyle on 9/26/2017.
 */
@Controller
@RequestMapping("/search")
public class SearchCrtl {

    @Autowired
    private ISearchSvc searchSvc;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("showNavButtons", true);
        model.addAttribute("pageName", "search");
        model.addAttribute("searchType", "movie");
        return "pages/search";
    }

    @ResponseBody
    @PostMapping("/movie")
    public List<MovieSearchResultVo> searchMovie(@RequestParam("query") String query) {
        return searchSvc.getMovies(query);
    }

    @ResponseBody
    @PostMapping("/tv")
    public List<TvSearchResultVo> searchTv(@RequestBody String query) {
        return searchSvc.getTv(query);
    }

    @ResponseBody
    @PostMapping("/addMovie")
    public DbResponseVo addMovie(@RequestParam("tmdbId") Integer tmdbId) {
        return searchSvc.addMovie(tmdbId);
    }

}
