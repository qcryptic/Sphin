package qcryptic.sphin.enums;

/**
 * Created by Kyle on 10/1/2017.
 */
public enum Endpoints {

    TVDB_INFO("https://www.thetvdb.com/?tab=series&id="),
    TMDB_INFO("https://www.themoviedb.org/movie/"),

    RADARR_SEARCH("/api/movies/lookup?APIKEY=<<key>>&term="),
    SONARR_SEARCH("/api/series/lookup?APIKEY=<<key>>&term="),

    DARR_ROOTFOLDER("/api/rootfolder?APIKEY=<<key>>"),
    DARR_PROFILES("/api/profile?APIKEY=<<key>>");

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
