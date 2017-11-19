package qcryptic.sphin.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import qcryptic.sphin.dao.IConnectionsDao;
import qcryptic.sphin.enums.ConnectionTypes;
import qcryptic.sphin.enums.Connections;

import java.sql.Types;

/**
 * Created by Kyle on 10/24/2017.
 */
@Repository
public class ConnectionsDao implements IConnectionsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String addConnection = "MERGE INTO CONNECTIONS KEY(NAME) VALUES(?, ?)";
    private static final String addActiveConnection = "MERGE INTO ACTIVE_CONNECTIONS KEY(TYPE) VALUES(?, ?)";
    private static final String getActiveConnection = "SELECT CONNECTIONS_NAME FROM ACTIVE_CONNECTIONS WHERE TYPE=?";
    private static final String getConnectionJson = "SELECT JSON FROM CONNECTIONS WHERE NAME=?";

    @Override
    public boolean updateConnection(Connections connectionName, ConnectionTypes connectionType, String json) {
        final Object[] params = new Object[] {connectionName.getName(), json};
        final int[] types = new int[] {Types.VARCHAR, Types.VARCHAR};
        int updatedRows = jdbcTemplate.update(addConnection, params, types);
        if (updatedRows != 1)
            return false;
        final Object[] params2 = new Object[] {connectionType.getName(), connectionName.getName()};
        final int[] types2 = new int[] {Types.VARCHAR, Types.VARCHAR};
        return jdbcTemplate.update(addActiveConnection, params2, types2) == 1;
    }

    @Override
    public Connections getActiveConnection(ConnectionTypes type) {
        try {
            String conn = jdbcTemplate.queryForObject(getActiveConnection, String.class, new Object[]{type.getName()}).toUpperCase();
            return Connections.valueOf(conn);
        } catch (EmptyResultDataAccessException e) {
            return Connections.NONE;
        }
    }

    @Override
    public String getConnectionJson(Connections connectionName) {
        try {
            return jdbcTemplate.queryForObject(getConnectionJson, String.class, new Object[]{connectionName.getName()});
        } catch (EmptyResultDataAccessException e) {
            return "none";
        }
    }
}
