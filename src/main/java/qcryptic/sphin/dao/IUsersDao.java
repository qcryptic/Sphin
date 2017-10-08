package qcryptic.sphin.dao;

/**
 * Created by Kyle on 10/7/2017.
 */
public interface IUsersDao {

    public boolean addUser(String name, String pass, Long uid);

    public boolean addAdmin(String name, String pass);

    public boolean checkAdminExists();

    public boolean checkUid();

    public boolean checkUser(String name);

    public String getPwHash(String name);

}
