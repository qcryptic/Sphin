package qcryptic.sphin.vo;

import lombok.Getter;
import lombok.Setter;
import qcryptic.sphin.enums.Endpoints;

/**
 * Created by Kyle on 10/1/2017.
 */
public class SearchResultVo {

    private @Getter @Setter String title;
    private @Getter @Setter Long id;
    private @Getter String posterUrl;
    private @Getter @Setter Integer year;
    private @Getter @Setter String overview;
    private @Getter @Setter Double rating;
    private @Getter @Setter Integer voteCount;
    private @Getter @Setter String[] genres;
    private @Getter @Setter String infoUrl;

    public SearchResultVo(String error, String message) {
        this.title = error;
        this.overview = message;
    }

    public SearchResultVo(String title, Long id, String posterUrl, Integer year, String overview, Double rating, Integer voteCount, String infoUrl) {
        this.title = title;
        this.id = id;
        if ("none".equals(posterUrl))
            this.posterUrl = "/img/default_poster.png";
        else
            this.posterUrl = posterUrl;
        this.year = year;
        this.overview = overview;
        this.rating = rating;
        this.voteCount = voteCount;
        this.infoUrl = infoUrl;
    }

    public void setPosterUrl(String posterUrl) {
        if ("none".equals(posterUrl))
            this.posterUrl = "/img/default_poster.png";
        else
            this.posterUrl = posterUrl;
    }

}
