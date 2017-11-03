package qcryptic.sphin.vo.connections;

import java.util.Map;

/**
 * Created by Kyle on 10/28/2017.
 */
public class DarrVo {

    private String url;
    private String api;
    private Integer pathId;
    private Integer profileId;
    private Map<Integer,String> profiles;
    private Map<Integer,String> paths;

    public DarrVo(String url, String api, Integer pathId, Integer profileId) {
        this.url = url;
        this.api = api;
        this.pathId = pathId;
        this.profileId = profileId;
        if (this.pathId == null)
            this.pathId = -1;
        if (this.profileId == null)
            this.profileId = -1;
    }

    public DarrVo(String url, String api, Integer pathId, Integer profileId, Map<Integer, String> profiles, Map<Integer, String> paths) {
        this.url = url;
        this.api = api;
        this.pathId = pathId;
        this.profileId = profileId;
        this.profiles = profiles;
        this.paths = paths;
        if (this.pathId == null)
            this.pathId = -1;
        if (this.profileId == null)
            this.profileId = -1;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Integer getPathId() {
        return pathId;
    }

    public void setPathId(Integer pathId) {
        if (pathId == null)
            this.pathId = -1;
        else
            this.pathId = pathId;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        if (profileId == null)
            this.profileId = -1;
        else
            this.profileId = profileId;
    }

    public Map<Integer, String> getProfiles() {
        return profiles;
    }

    public void setProfiles(Map<Integer, String> profiles) {
        this.profiles = profiles;
    }

    public Map<Integer, String> getPaths() {
        return paths;
    }

    public void setPaths(Map<Integer, String> paths) {
        this.paths = paths;
    }

    public String getJsonString() {
        return "{\"url\":\""+this.url+"\",\"api\":\""+this.api+"\",\"path\":"+this.pathId+",\"profile\":"+this.profileId+"}";
    }

}
