package qcryptic.sphin.service;

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
    public List<MovieSearchResultVo> getMovies(String query);

    /**
     *
     * @return
     */
    public List<TvSearchResultVo> getTv(String query);

}