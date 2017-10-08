package qcryptic.sphin.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import qcryptic.sphin.dao.IUsersDao;

import java.sql.Types;

/**
 * Created by Kyle on 10/7/2017.
 */
@Repository
public class UsersDao implements IUsersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String addAdmin = "insert into users values (users_seq.nextval, ?, ?, 0)";
    private static final String adminCount = "select count(*) from users where rank = 0";

    //TODO: Fix to use uid
    @Override
    public boolean addUser(String name, String pass, Long uid) {
        final Object[] params = new Object[] {name, pass, uid};
        final int[] types = new int[] {Types.VARCHAR, Types.VARCHAR, Types.NUMERIC};
        return jdbcTemplate.update(addAdmin, params, types) == 1;
    }

    @Override
    public boolean addAdmin(String name, String pass) {
        final Object[] params = new Object[] {name, pass};
        final int[] types = new int[] {Types.VARCHAR, Types.VARCHAR};
        return jdbcTemplate.update(addAdmin, params, types) == 1;
    }

    @Override
    public boolean checkAdminExists() {
        return jdbcTemplate.queryForObject(adminCount, Integer.class) > 0;
    }

    @Override
    public boolean checkUid() {
        return false;
    }

}
