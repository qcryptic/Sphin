package qcryptic.sphin.enums;

/**
 * Created by Kyle on 11/18/2017.
 */
public enum Connections {

    RADARR("radarr"),
    SONARR("sonarr"),
    NZBGET("nzbget"),
    SABNZBD("sabnzbd"),
    COUCHPOTATO("couchpotato"),
    WATCHER("watcher"),
    SICKRAGE("sickrage"),
    SICKBEARD("sickbeard"),
    NONE("none");

    private String name;

    Connections(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
