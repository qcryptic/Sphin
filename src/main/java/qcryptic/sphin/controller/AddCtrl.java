package qcryptic.sphin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Kyle on 10/3/2017.
 */
@Controller
@RequestMapping("/add")
public class AddCtrl {

    @ResponseBody
    @PostMapping("/movie")
    public String addMovie(@RequestBody Long tmdbId) {
        return null;
    }

    @ResponseBody
    @PostMapping("/tv")
    public String addTv(@RequestBody Long tvdbId) {
        return null;
    }

}