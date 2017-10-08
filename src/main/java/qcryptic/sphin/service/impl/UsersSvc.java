package qcryptic.sphin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import qcryptic.sphin.dao.IUsersDao;
import qcryptic.sphin.service.IUsersSvc;
import qcryptic.sphin.vo.DbResponseVo;

/**
 * Created by Kyle on 10/7/2017.
 */
@Service
public class UsersSvc implements IUsersSvc {

    @Autowired
    private IUsersDao usersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DbResponseVo addAdmin(String user, String pass) {
        if (usersDao.checkAdminExists())
            return new DbResponseVo(false, "Admin account already exists, you cannot make another");
        pass = passwordEncoder.encode(pass);
        boolean result = usersDao.addAdmin(user, pass);
        if (result)
            return new DbResponseVo(result, "Admin account created");
        return new DbResponseVo(result, "Error adding admin account, please check the logs");
    }

    @Override
    public boolean addUser(String user, String name, Long uid) {
        return usersDao.addUser(user, name, uid);
    }

    @Override
    public boolean checkAdminExists() {
        return usersDao.checkAdminExists();
    }
}
