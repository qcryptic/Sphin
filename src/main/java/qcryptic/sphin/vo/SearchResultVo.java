package qcryptic.sphin.vo;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
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
    private @Getter @Setter Integer profileId;
    private @Getter @Setter String titleSlug;
    private @Getter @Setter String path;
    private @Getter @Setter String images;

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

    public SearchResultVo(String title, Long id, String posterUrl, Integer year, String overview, Double rating, String infoUrl, Integer profileId, String titleSlug, String path, String images) {
        this.title = title;
        this.id = id;
        if ("none".equals(posterUrl))
            this.posterUrl = "/img/default_poster.png";
        else
            this.posterUrl = posterUrl;
        this.year = year;
        this.overview = overview;
        this.rating = rating;
        this.infoUrl = infoUrl;
        this.profileId = profileId;
        this.titleSlug = titleSlug;
        this.path = path;
        this.images = images;
    }

    public void setPosterUrl(String posterUrl) {
        if ("none".equals(posterUrl))
            this.posterUrl = "/img/default_poster.png";
        else
            this.posterUrl = posterUrl;
    }

}
