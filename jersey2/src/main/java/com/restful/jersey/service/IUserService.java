package com.restful.jersey.service;

import java.util.List;

import com.restful.jersey.domain.User;


public interface IUserService {

    long insert(User user);

    List<User> getUserList();

    User findUserById(long id);

    User findUserByName(String username);

    int update(User user);

    int delete();  

    int deleteById(long id);
}
