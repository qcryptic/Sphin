package qcryptic.sphin.service;

import qcryptic.sphin.vo.DbResponseVo;
import qcryptic.sphin.vo.SearchResultVo;

import java.util.List;

/**
 * Created by Kyle on 10/1/2017.
 */
public interface ISearchSvc {

    /**
     *
     * @return
     */
    List<SearchResultVo> getMovies(String query);

    /**
     *
     * @return
     */
    List<SearchResultVo> getTv(String query);

    DbResponseVo addMovie(Integer tmdbId);

    DbResponseVo addTv(Integer tvdbId);

}
