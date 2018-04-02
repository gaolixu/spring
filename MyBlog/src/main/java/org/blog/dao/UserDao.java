package org.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.blog.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by blog on 17-3-10.
 */
@Repository
public interface UserDao {
    public User getUser(@Param("username") String username, @Param("password") String password);
}
