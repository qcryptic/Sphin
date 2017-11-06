package qcryptic.sphin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import qcryptic.sphin.dao.IConnectionsDao;
import qcryptic.sphin.enums.Endpoints;
import qcryptic.sphin.service.ISearchSvc;
import qcryptic.sphin.vo.DbResponseVo;
import qcryptic.sphin.vo.SearchResultVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 10/1/2017.
 */
@Slf4j
@Service
public class SearchSvc implements ISearchSvc {

    @Autowired
    private IConnectionsDao connectionsDao;

    @Override
    public List<SearchResultVo> getMovies(String query) {
        ArrayList<SearchResultVo> movies = new ArrayList<>();
        String activeMovieManager = connectionsDao.getActiveConnection("movie");
        if ("none".equals(activeMovieManager)) {
            movies.add(new SearchResultVo("badrequest", "Movie manager required - set one up in connections settings"));
            return movies;
        }
        String movieManagerJson = connectionsDao.getConnectionJson(activeMovieManager);
        if ("none".equals(movieManagerJson)) {
            movies.add(new SearchResultVo("badrequest", "Movie manager required - set one up in connections settings"));
            return movies;
        }
        Endpoints endpoint = Endpoints.RADARR_SEARCH; //Update if/when more movie managers are added
        try {
            JSONObject movieManager = new JSONObject(movieManagerJson);
            String urlString = movieManager.getString("url") + endpoint.getUrl(movieManager.getString("api")) + query;
            RestTemplate rt = new RestTemplate();
            JSONArray moviesJson = new JSONArray(rt.getForObject(urlString, String.class));
            for (int i = 0; i < moviesJson.length(); i++) {
                JSONObject movie = moviesJson.getJSONObject(i);
                String poster;
                poster = movie.getString("remotePoster");
                if ("http://image.tmdb.org/t/p/original".equals(poster))
                    poster = "none";
                poster = poster.replace("/t/p/original", "/t/p/w185");
                poster = poster.replace("http:", "https:");
                JSONObject ratings = movie.getJSONObject("ratings");
                Long id = movie.getLong("tmdbId");
                movies.add(new SearchResultVo(movie.getString("title"), id, poster, movie.getInt("year"), movie.getString("overview"),
                        ratings.getDouble("value"), ratings.getInt("votes"), Endpoints.TMDB_INFO.getUrl() + id));
            }
        } catch (Exception e) {
            log.error("Error in SearchSvc - getMovies()", e);
        }
        return movies;
    }

    @Override
    public List<SearchResultVo> getTv(String query) {
        ArrayList<SearchResultVo> shows = new ArrayList<>();
        String activeTvManager = connectionsDao.getActiveConnection("movie");
        if ("none".equals(activeTvManager)) {
            shows.add(new SearchResultVo("badrequest", "TV manager required - set one up in connections settings"));
            return shows;
        }
        String tvManagerJson = connectionsDao.getConnectionJson(activeTvManager);
        if ("none".equals(tvManagerJson)) {
            shows.add(new SearchResultVo("badrequest", "TV manager required - set one up in connections settings"));
            return shows;
        }
        Endpoints endpoint = Endpoints.SONARR_SEARCH; //Update if/when more tv managers are added
        try {
            JSONObject tvManager = new JSONObject(tvManagerJson);
            String urlString = tvManager.getString("url") + endpoint.getUrl(tvManager.getString("api")) + query;
            RestTemplate rt = new RestTemplate();
            JSONArray showsJson = new JSONArray(rt.getForObject(urlString, String.class));
            for (int i = 0; i < showsJson.length(); i++) {
                JSONObject show = showsJson.getJSONObject(i);
                String poster = (show.has("remotePoster")) ? show.getString("remotePoster") : "none";
                poster = poster.replace("http:", "https:");
                String overview = (show.has("overview")) ? show.getString("overview") : "No summary found.";
                Integer year = (show.has("year")) ? show.getInt("year") : 0;
                Long id = show.getLong("tvdbId");
                JSONObject ratings = show.getJSONObject("ratings");
                shows.add(new SearchResultVo(show.getString("title"), id, poster, year, overview, ratings.getDouble("value"),
                        ratings.getInt("votes"), Endpoints.TVDB_INFO.getUrl() + id));
            }
        } catch (Exception e) {
            log.error("Error in SearchSvc - getMovies()", e);
        }
        return shows;
    }

    @Override
    public DbResponseVo addMovie(Integer tmdbId) {
        return new DbResponseVo(true, "hitting the svc MOVIE");
    }

    @Override
    public DbResponseVo addTv(Integer tvdbId) {
        return new DbResponseVo(true, "hitting the svc TV");
    }
}
