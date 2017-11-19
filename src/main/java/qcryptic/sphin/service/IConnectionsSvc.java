package qcryptic.sphin.service;

import qcryptic.sphin.enums.ConnectionTypes;
import qcryptic.sphin.enums.Connections;
import qcryptic.sphin.vo.DbResponseVo;
import qcryptic.sphin.vo.connections.DarrVo;

/**
 * Created by Kyle on 10/24/2017.
 */
public interface IConnectionsSvc {

    DbResponseVo updateConnection(Connections connectionName, ConnectionTypes connectionType, String json);

    DbResponseVo updateConnection(Connections connectionName, ConnectionTypes connectionType, DarrVo darrVo);

    Connections getActiveConnection(ConnectionTypes connectionType);

    DbResponseVo testDarr(String url, String api);

    DarrVo getDarrInfo(Connections type);

}
