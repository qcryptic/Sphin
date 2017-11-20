package qcryptic.sphin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import qcryptic.sphin.dao.IConnectionsDao;
import qcryptic.sphin.enums.Connections;
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
    public List<SearchResultVo> getMoviesRadarr(String query) {
        ArrayList<SearchResultVo> movies = new ArrayList<>();
        String radarrJson = connectionsDao.getConnectionJson(Connections.RADARR);
        if ("none".equals(radarrJson)) {
            movies.add(new SearchResultVo("badrequest", "Movie manager required - set one up in connections settings"));
            return movies;
        }
        try {
            JSONObject radarrInfo = new JSONObject(radarrJson);
            String urlString = radarrInfo.getString("url") + Endpoints.RADARR_SEARCH.getUrl(radarrInfo.getString("api")) + query;
            RestTemplate rt = new RestTemplate();
            JSONArray results = new JSONArray(rt.getForObject(urlString, String.class));
            for (int i = 0; i < results.length(); i++) {
                JSONObject movie = results.getJSONObject(i);
                String poster;
                poster = movie.getString("remotePoster");
                if ("http://image.tmdb.org/t/p/original".equals(poster))
                    poster = "none";
                poster = poster.replace("/t/p/original", "/t/p/w185");
                poster = poster.replace("http:", "https:");
                JSONObject ratings = movie.getJSONObject("ratings");
                JSONArray images = movie.getJSONArray("images");
                Long id = movie.getLong("tmdbId");
                movies.add(new SearchResultVo(movie.getString("title"), id, poster, movie.getInt("year"), movie.getString("overview"), ratings.getDouble("value"),
                        Endpoints.TMDB_INFO.getUrl() + id, radarrInfo.getInt("profile"), movie.getString("titleSlug"), radarrInfo.getString("pathName"), images.toString()));
            }
        } catch (Exception e) {
            log.error("Error in SearchSvc - getMoviesRadarr()", e);
            movies.add(new SearchResultVo("badrequest", "Error occurred while searching for movies!"));
        }
        return movies;
    }

    @Override
    public List<SearchResultVo> getTvSonarr(String query) {
        ArrayList<SearchResultVo> shows = new ArrayList<>();
        String tvManagerJson = connectionsDao.getConnectionJson(Connections.SONARR);
        if ("none".equals(tvManagerJson)) {
            shows.add(new SearchResultVo("badrequest", "TV manager required - set one up in connections settings"));
            return shows;
        }
        try {
            JSONObject sonarrInfo = new JSONObject(tvManagerJson);
            String urlString = sonarrInfo.getString("url") + Endpoints.SONARR_SEARCH.getUrl(sonarrInfo.getString("api")) + query;
            RestTemplate rt = new RestTemplate();
            JSONArray results = new JSONArray(rt.getForObject(urlString, String.class));
            for (int i = 0; i < results.length(); i++) {
                JSONObject show = results.getJSONObject(i);
                String poster = (show.has("remotePoster")) ? show.getString("remotePoster") : "none";
                poster = poster.replace("http:", "https:");
                String overview = (show.has("overview")) ? show.getString("overview") : "No summary found.";
                Long id = show.getLong("tvdbId");
                JSONArray images = show.getJSONArray("images");
                JSONArray seasons = show.getJSONArray("seasons");
                JSONObject ratings = show.getJSONObject("ratings");
                shows.add(new SearchResultVo(show.getString("title"), id, poster, show.getInt("year"), overview, ratings.getDouble("value"), Endpoints.TVDB_INFO.getUrl() + id,
                        sonarrInfo.getInt("profile"), show.getString("titleSlug"), sonarrInfo.getString("pathName"), images.toString(), seasons.toString()));
            }
        } catch (Exception e) {
            log.error("Error in SearchSvc - getTvSonarr()", e);
            shows.add(new SearchResultVo("badrequest", "Error occurred while searching for TV shows!"));
        }
        return shows;
    }

    @Override
    public DbResponseVo addMovieRadarr(Integer tmdbId, String title, String titleSlug, Integer profileId, String path, JSONArray images)  {
        String radarrJson = connectionsDao.getConnectionJson(Connections.RADARR);
        if ("none".equals(radarrJson)) {
            return new DbResponseVo(false, "Error - no valid movie manager found, set one up in the connection settings");
        }
        JSONObject radarrInfo = new JSONObject(radarrJson);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject body = new JSONObject();
        body.put("tmdbId", tmdbId);
        body.put("title", title);
        body.put("titleSlug", titleSlug);
        body.put("qualityProfileId", profileId);
        body.put("rootFolderPath", path);
        body.put("images", images);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", radarrInfo.getString("api"));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body.toString(),headers);
        try {
            String answer = restTemplate.postForObject(radarrInfo.getString("url")+"/api/movie", entity, String.class);
            return new DbResponseVo(true, answer);
        } catch (HttpStatusCodeException e) {
            return httpStatusExemptionResponse(e);
        } catch (ResourceAccessException e) {
            return new DbResponseVo(false, "Invalid URL! Check your movie manager settings");
        }
    }

    @Override
    public DbResponseVo addTvSonarr(Integer tvdbId, String title, String titleSlug, Integer profileId, String path, JSONArray images, JSONArray seasons) {
        String radarrJson = connectionsDao.getConnectionJson(Connections.SONARR);
        if ("none".equals(radarrJson)) {
            return new DbResponseVo(false, "Error - no valid TV manager found, set one up in the connection settings");
        }
        JSONObject sonarrInfo = new JSONObject(radarrJson);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject body = new JSONObject();
        body.put("tvdbId", tvdbId);
        body.put("title", title);
        body.put("titleSlug", titleSlug);
        body.put("qualityProfileId", profileId);
        body.put("rootFolderPath", path);
        body.put("images", images);
        body.put("seasons", seasons);
        //body.put("addOptions", new JSONObject().put("ignoreEpisodesWithFiles",true).put("ignoreEpisodesWithoutFiles",true).put("searchForMissingEpisodes",false));
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", sonarrInfo.getString("api"));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body.toString(),headers);
        try {
            String answer = restTemplate.postForObject(sonarrInfo.getString("url")+"/api/series", entity, String.class);
            return new DbResponseVo(true, answer);
        } catch (HttpStatusCodeException e) {
            return httpStatusExemptionResponse(e);
        } catch (ResourceAccessException e) {
            return new DbResponseVo(false, "Invalid URL! Check your TV manager settings");
        }
    }

    private DbResponseVo httpStatusExemptionResponse(HttpStatusCodeException e) {
        int err = e.getStatusCode().value();
        if (err == 400)
            return new DbResponseVo(false, e.getResponseBodyAsString());
        else if (err == 401)
            return new DbResponseVo(false, "Unauthorized! Make sure your API key is correct");
        else
            return new DbResponseVo(false, e.getMessage());
    }

}
