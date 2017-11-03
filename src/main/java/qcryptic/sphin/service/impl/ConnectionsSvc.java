package qcryptic.sphin.service.impl;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import qcryptic.sphin.dao.IConnectionsDao;
import qcryptic.sphin.enums.Endpoints;
import qcryptic.sphin.service.IConnectionsSvc;
import qcryptic.sphin.utils.SphinUtils;
import qcryptic.sphin.vo.DbResponseVo;
import qcryptic.sphin.vo.HttpResponseVo;
import qcryptic.sphin.vo.connections.DarrVo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kyle on 10/24/2017.
 */
@Service
public class ConnectionsSvc implements IConnectionsSvc {

    private static final Logger LOGGER = Logger.getLogger(ConnectionsSvc.class.getName());

    @Autowired
    private IConnectionsDao connectionsDao;

    @Override
    public DbResponseVo updateConnection(String connectionName, String connectionType, String json) {
        boolean isSuccessful = connectionsDao.updateConnection(connectionName, connectionType, json);
        if (!isSuccessful)
            return new DbResponseVo(false, "Error updating connection info");
        else
            return new DbResponseVo(true, "success");
    }

    @Override
    public DbResponseVo updateConnection(String connectionName, String connectionType, DarrVo darrVo) {
        HttpResponseVo status = SphinUtils.getHttpStatus(darrVo.getUrl() + Endpoints.DARR_ROOTFOLDER.getUrl(darrVo.getApi()));
        if (status.getCode() != 200)
            return new DbResponseVo(false, "Connection check failed! Use the test connection button");
        boolean isSuccessful = connectionsDao.updateConnection(connectionName, connectionType, darrVo.getJsonString());
        if (!isSuccessful)
            return new DbResponseVo(false, "Error updating connection info");
        else
            return new DbResponseVo(true, "success");
    }

    @Override
    public String getActiveConnection(String connectionType) {
        return connectionsDao.getActiveConnection(connectionType);
    }

    @Override
    public DbResponseVo testDarr(String url, String api) {
        HttpResponseVo status = SphinUtils.getHttpStatus(url + Endpoints.DARR_ROOTFOLDER.getUrl(api));
        if (status.getCode() != 200)
            return new DbResponseVo(false, "Connection error: "+status.getMessage());
        RestTemplate rt = new RestTemplate();
        try {
            String paths = rt.getForObject(url + Endpoints.DARR_ROOTFOLDER.getUrl(api), String.class);
            String profiles = rt.getForObject(url + Endpoints.DARR_PROFILES.getUrl(api), String.class);
            return new DbResponseVo(true, "{\"paths\":"+paths+",\"profiles\":"+profiles+"}");
        } catch (Exception e) {
            return new DbResponseVo(false, e.getMessage());
        }
    }

    @Override
    public DarrVo getDarrInfo(String type) throws JSONException {
        Map<Integer, String> paths = new HashMap<>();
        Map<Integer, String> profiles = new HashMap<>();
        String json = connectionsDao.getConnectionJson(type);
        if ("none".equals(json))
            return new DarrVo("","",-1,-1);
        JSONObject darrJson = new JSONObject(json);
        String url = darrJson.getString("url");
        String api = darrJson.getString("api");
        try {
            RestTemplate rt = new RestTemplate();
            String path = rt.getForObject(url + Endpoints.DARR_ROOTFOLDER.getUrl(api), String.class);
            String profile = rt.getForObject(url + Endpoints.DARR_PROFILES.getUrl(api), String.class);
            JSONArray pathJson = new JSONArray(path);
            JSONArray profileJson = new JSONArray(profile);
            for (int i = 0; i < pathJson.length(); i++) {
                JSONObject temp = (JSONObject)pathJson.get(i);
                paths.put(temp.getInt("id"), temp.getString("path"));
            }
            for (int i = 0; i < profileJson.length(); i++) {
                JSONObject temp = (JSONObject)profileJson.get(i);
                profiles.put(temp.getInt("id"), temp.getString("name"));
            }
        } catch (Exception e) {
            return new DarrVo(url, api, darrJson.getInt("path"), darrJson.getInt("profile"));
        }
        return new DarrVo(url, api, darrJson.getInt("path"), darrJson.getInt("profile"), profiles, paths);
    }

}
