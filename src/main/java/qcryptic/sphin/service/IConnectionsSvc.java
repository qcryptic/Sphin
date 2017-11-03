package qcryptic.sphin.service;

import qcryptic.sphin.vo.DbResponseVo;
import qcryptic.sphin.vo.connections.DarrVo;

/**
 * Created by Kyle on 10/24/2017.
 */
public interface IConnectionsSvc {

    DbResponseVo updateConnection(String connectionName, String connectionType, String json);

    DbResponseVo updateConnection(String connectionName, String connectionType, DarrVo darrVo);

    String getActiveConnection(String connectionType);

    DbResponseVo testDarr(String url, String api);

    DarrVo getDarrInfo(String type);

}
