package qcryptic.sphin.vo;

import qcryptic.sphin.enums.Endpoints;

/**
 * Created by Kyle on 10/1/2017.
 */
public class MovieSearchResultVo {

    private String title;
    private Long id;
    private String posterUrl;
    private String year;
    private String overview;
    private Double rating;
    private Integer voteCount;
    private String[] genres;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        if ("none".equals(posterUrl))
            this.posterUrl = "/img/default_poster.png";
        else
            this.posterUrl = Endpoints.TMDB_PICTURES.getUrl() + posterUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genreIds) {
        this.genres = genreIds;
    }
}
