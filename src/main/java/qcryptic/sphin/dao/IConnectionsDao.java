package qcryptic.sphin.dao;

import qcryptic.sphin.enums.ConnectionTypes;
import qcryptic.sphin.enums.Connections;

/**
 * Created by Kyle on 10/24/2017.
 */
public interface IConnectionsDao {

    boolean updateConnection(Connections connectionName, ConnectionTypes connectionType, String json);

    Connections getActiveConnection(ConnectionTypes type);

    String getConnectionJson(Connections connectionName);

}
