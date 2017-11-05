package qcryptic.sphin.vo;

import lombok.Getter;
import lombok.Setter;
import qcryptic.sphin.enums.Endpoints;

/**
 * Created by Kyle on 10/1/2017.
 */
public class MovieSearchResultVo {

    private @Getter @Setter String title;
    private @Getter @Setter Long id;
    private @Getter String posterUrl;
    private @Getter @Setter String year;
    private @Getter @Setter String overview;
    private @Getter @Setter Double rating;
    private @Getter @Setter Integer voteCount;
    private @Getter @Setter String[] genres;

    public MovieSearchResultVo(String title, Long id, String posterUrl, String year, String overview, Double rating, Integer voteCount) {
        this.title = title;
        this.id = id;
        if ("none".equals(posterUrl))
            this.posterUrl = "/img/default_poster.png";
        else
            this.posterUrl = Endpoints.TMDB_PICTURES.getUrl() + posterUrl;
        this.year = year;
        this.overview = overview;
        this.rating = rating;
        this.voteCount = voteCount;
    }

    public void setPosterUrl(String posterUrl) {
        if ("none".equals(posterUrl))
            this.posterUrl = "/img/default_poster.png";
        else
            this.posterUrl = Endpoints.TMDB_PICTURES.getUrl() + posterUrl;
    }

}
