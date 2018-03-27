package com.restful.jersey.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.restful.jersey.domain.User;
import com.restful.jersey.service.IUserService;


@Service("userService")
public class UserServiceImpl implements IUserService {
	
	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());


    private AtomicLong counter = new AtomicLong(1);

    public long insert(User user) {

        return counter.getAndIncrement();
    }

    public List<User> getUserList() {

        List<User> userList = new ArrayList<User>();
        for(int i=0;i<10;i++){

            User user = new User();
            user.setId(counter.getAndIncrement());
            user.setName("abc_"+i);
            user.setAge(25);

            userList.add(user);
        }

        return userList;
    }

    public User findUserById(long id) {
    	
LOGGER.error("test error");
		
		LOGGER.info("test info");
		
		LOGGER.debug("test debug");
        User user = new User();
        user.setId(id);
        user.setName("ricky");
        user.setAge(27);

        return user;
    }

    public User findUserByName(String username) {

        User user = new User();
        user.setId(counter.getAndIncrement());
        user.setName(username);
        user.setAge(25);

        return user;
    }

    public int update(User user) {

        return 1;
    }

    public int delete() {
        return (int) counter.get();
    }

    public int deleteById(long id) {
        return 1;
    }
}
