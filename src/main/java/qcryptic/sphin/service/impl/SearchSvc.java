package qcryptic.sphin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import qcryptic.sphin.enums.Endpoints;
import qcryptic.sphin.service.ISearchSvc;
import qcryptic.sphin.utils.genericUtils;
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
@Service
public class SearchSvc implements ISearchSvc {

    @Override
    public List<MovieSearchResultVo> getMovies(String query) {
        ArrayList<MovieSearchResultVo> movies = new ArrayList<>();
        query = query.replaceAll(" ", "%20");
        try {
            String urlString = Endpoints.TMDB_SEARCH.getUrl("317e36b902ac8fdb2529eb94ae93ecd3") + query;
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Accept-Charset", "ISO-8859-1");
            con.setRequestMethod("GET");
            JSONObject json = genericUtils.readJson(con.getInputStream());
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public List<TvSearchResultVo> getTv(String query) {
        return null;
    }

}
