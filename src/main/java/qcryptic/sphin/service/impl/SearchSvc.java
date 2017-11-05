package qcryptic.sphin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import qcryptic.sphin.enums.Endpoints;
import qcryptic.sphin.service.ISearchSvc;
import qcryptic.sphin.utils.SphinUtils;
import qcryptic.sphin.vo.DbResponseVo;
import qcryptic.sphin.vo.MovieSearchResultVo;
import qcryptic.sphin.vo.TvSearchResultVo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 10/1/2017.
 */
@Slf4j
@Service
public class SearchSvc implements ISearchSvc {

    @Override
    public List<MovieSearchResultVo> getMovies(String query) {
        ArrayList<MovieSearchResultVo> movies = new ArrayList<>();
        String urlString = Endpoints.TMDB_SEARCH.getUrl("317e36b902ac8fdb2529eb94ae93ecd3") + query;
        RestTemplate rt = new RestTemplate();
        try {
            JSONObject json = new JSONObject(rt.getForObject(urlString, String.class));
            JSONArray moviesJson = json.getJSONArray("results");
            for (int i = 0; i < moviesJson.length(); i++) {
                JSONObject movie = moviesJson.getJSONObject(i);
                String poster;
                if (movie.isNull("poster_path"))
                    poster = "none";
                else
                    poster = movie.getString("poster_path");
                String year = StringUtils.left(movie.getString("release_date"), 4);
                movies.add(new MovieSearchResultVo(movie.getString("title"), movie.getLong("id"), poster, year,
                        movie.getString("overview"), movie.getDouble("vote_average"), movie.getInt("vote_count")));
            }
        } catch (Exception e) {
            log.error("Error in SearchSvc - getMovies()", e);
        }
        return movies;
    }

    @Override
    public List<TvSearchResultVo> getTv(String query) {
        return null;
    }

    @Override
    public DbResponseVo addMovie(Integer tmdbId) {
        return new DbResponseVo(true, "hitting the svc");
    }
}
