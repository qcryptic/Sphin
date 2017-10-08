package qcryptic.sphin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
        if (usersDao.checkUser(user))
            return new DbResponseVo(false, "Username already exists, pick a new one");
        pass = passwordEncoder.encode(pass);
        boolean result = usersDao.addAdmin(user, pass);
        if (result)
            return new DbResponseVo(true, "Admin account created");
        return new DbResponseVo(false, "Error adding admin account, please check the logs");
    }

    @Override
    public boolean addUser(String user, String name, Long uid) {
        //if (usersDao.checkUser(user))
        //    return new DbResponseVo(false, "Username already exists, pick a new one");
        return usersDao.addUser(user, name, uid);
    }

    @Override
    public boolean checkAdminExists() {
        return usersDao.checkAdminExists();
    }

    @Override
    public DbResponseVo login(String user, String pass) {
        if (!usersDao.checkUser(user))
            return new DbResponseVo(false, "Username not found");
        String stored_hash = usersDao.getPwHash(user);
        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        if (BCrypt.checkpw(pass, stored_hash))
            return new DbResponseVo(true, "Login successful");
        return new DbResponseVo(false, "Invalid password");
    }
}
