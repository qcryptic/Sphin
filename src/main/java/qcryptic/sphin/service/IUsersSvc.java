package qcryptic.sphin.service;

import qcryptic.sphin.vo.DbResponseVo;

/**
 * Created by Kyle on 10/7/2017.
 */
public interface IUsersSvc {

    DbResponseVo addAdmin(String user, String pass);

    DbResponseVo login(String user, String pass);

    boolean addUser(String user, String name, Long uid);

    boolean checkAdminExists();

    DbResponseVo generateInviteLink(String rank, Integer hours);

    boolean addRank(String rank);

}
