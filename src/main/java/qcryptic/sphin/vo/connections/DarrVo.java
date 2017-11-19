package qcryptic.sphin.vo.connections;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by Kyle on 10/28/2017.
 */
public class DarrVo {

    private @Getter @Setter String url;
    private @Getter @Setter String api;
    private @Getter Integer pathId;
    private @Getter Integer profileId;
    private @Getter @Setter String pathName;
    private @Getter @Setter Map<Integer,String> profiles;
    private @Getter @Setter Map<Integer,String> paths;

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

    public DarrVo(String url, String api, Integer pathId, Integer profileId, String pathName) {
        this.url = url;
        this.api = api;
        this.pathId = pathId;
        this.profileId = profileId;
        this.pathName = pathName;
        if (this.pathId == null)
            this.pathId = -1;
        if (this.profileId == null)
            this.profileId = -1;
        if (this.pathName == null)
            this.pathName = "";
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

    public void setPathId(Integer pathId) {
        if (pathId == null)
            this.pathId = -1;
        else
            this.pathId = pathId;
    }

    public void setProfileId(Integer profileId) {
        if (profileId == null)
            this.profileId = -1;
        else
            this.profileId = profileId;
    }

    public String getJsonString() {
        return "{\"url\":\""+this.url+"\",\"api\":\""+this.api+"\",\"path\":"+this.pathId+",\"profile\":"+this.profileId+",\"pathName\":\"" + this.pathName + "\"}";
    }

}
