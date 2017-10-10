package qcryptic.sphin.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private static final String addRank = "insert into ranks values (ranks_seq.nextval, ?)";
    private static final String addInvite =
            "insert into invites values (invites_seq.nextval, ?, 1, null, select id from ranks where name = ?, DATEADD('HOUR', ?, CURRENT_TIMESTAMP()))";
    private static final String adminCount = "select count(*) from users where rank = 0";
    private static final String getPwHash = "select password from users where username = ?";
    private static final String checkUser = "select count(*) from users where username = ?";

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

    @Override
    public String getPwHash(String name) {
        try {
            return jdbcTemplate.queryForObject(getPwHash, String.class, new Object[]{name});
        }
        catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkUser(String name) {
        return jdbcTemplate.queryForObject(checkUser, Integer.class, new Object[]{name}) > 0;
    }

    @Override
    public boolean addInvite(String uid, String rank, Integer hours) {
        final Object[] params = new Object[] {uid, rank, hours};
        final int[] types = new int[] {Types.VARCHAR, Types.VARCHAR, Types.NUMERIC};
        return jdbcTemplate.update(addInvite, params, types) == 1;
    }

    @Override
    public boolean addRank(String name) {
        return jdbcTemplate.update(addRank, new Object[] {name}, new int[] {Types.VARCHAR}) == 1;
    }
}
