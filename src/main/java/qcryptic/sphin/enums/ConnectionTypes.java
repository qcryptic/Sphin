package qcryptic.sphin.enums;

/**
 * Created by Kyle on 11/18/2017.
 */
public enum ConnectionTypes {

    MOVIE("movie"),
    TV("tv"),
    DOWNLOADER("downloader");

    private String name;

    ConnectionTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
