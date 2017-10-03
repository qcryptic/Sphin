package qcryptic.sphin.enums;

/**
 * Created by Kyle on 10/1/2017.
 */
public enum Endpoints {

    TMDB_GENRES("https://api.themoviedb.org/3/genre/movie/list?api_key=<<key>>&language=en-US"),
    TMDB_SEARCH("https://api.themoviedb.org/3/search/movie?api_key=<<key>>&language=en-US&include_adult=false&query="),
    TMDB_PICTURES("https://image.tmdb.org/t/p/w185");

    private String url;

    Endpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUrl(String key) {
        return this.url.replace("<<key>>", key);
    }
}
