package qcryptic.sphin.service;

import org.json.JSONArray;
import qcryptic.sphin.vo.DbResponseVo;
import qcryptic.sphin.vo.SearchResultVo;

import java.util.List;

/**
 * Created by Kyle on 10/1/2017.
 */
public interface ISearchSvc {

    List<SearchResultVo> getMoviesRadarr(String query);

    List<SearchResultVo> getTvSonarr(String query);

    DbResponseVo addMovieRadarr(Integer tmdbId, String title, String titleSlug, Integer profileId, String path, JSONArray images);

    DbResponseVo addTvSonarr(Integer tvdbId);

}
