package org.mybatis.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.bean.Person;
import org.mybatis.bean.User;
import org.mybatis.bean.UserParams;

/**
 * Created by mybatis on 17-1-13.
 */
public interface UserMapper {
    public List<User> getUser();
    public List<User> getUser2();
    public List<Person> getPerson();

    public int insertUser(User user);
    public int insertUser2(User user);
    public int insertUser3(User user);
    public int insertUser4(User user);
    public int insertPerson(Person p);
    
    
    public User getUser2(Long id);


    public int deleteUser(Long id);
    
    
//  @Select("select * from user")
  public List<User> getAll();
  
  
  public List<User> getUserByAddressAndName(@Param("username") String username, @Param("address") String address);
  public ArrayList<User> getUserByAddressAndName2(Map<String,String> map);
  public ArrayList<User> getUserByAddressAndName3(UserParams params);
  
  public int getCount();

}
