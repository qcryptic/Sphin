package qcryptic.sphin.service;

import qcryptic.sphin.vo.DbResponseVo;
import qcryptic.sphin.vo.MovieSearchResultVo;
import qcryptic.sphin.vo.TvSearchResultVo;

import java.util.List;

/**
 * Created by Kyle on 10/1/2017.
 */
public interface ISearchSvc {

    /**
     *
     * @return
     */
    List<MovieSearchResultVo> getMovies(String query);

    /**
     *
     * @return
     */
    List<TvSearchResultVo> getTv(String query);

    DbResponseVo addMovie(Integer tmdbId);

}
