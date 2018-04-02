package org.blog.service;

import org.blog.dao.UserDao;
import org.blog.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by blog on 17-3-10.
 */
@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public boolean login(String username, String password) {
        User user = userDao.getUser(username, password);
        if (user == null) {
            return false;
        }else{
            return true;
        }
    }
}
