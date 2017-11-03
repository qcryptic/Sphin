package qcryptic.sphin.dao;

/**
 * Created by Kyle on 10/24/2017.
 */
public interface IConnectionsDao {

    boolean updateConnection(String connectionName, String connectionType, String json);

    String getActiveConnection(String type);

    String getConnectionJson(String connectionName);

}
