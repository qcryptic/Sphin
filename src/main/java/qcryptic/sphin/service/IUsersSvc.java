package qcryptic.sphin.service;

import qcryptic.sphin.vo.DbResponseVo;

/**
 * Created by Kyle on 10/7/2017.
 */
public interface IUsersSvc {

    public DbResponseVo addAdmin(String user, String pass);

    public boolean addUser(String user, String name, Long uid);

    public boolean checkAdminExists();

}
