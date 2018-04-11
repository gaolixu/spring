package org.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.mybatis.bean.Person;
import org.mybatis.bean.Province;
import org.mybatis.bean.User;
import org.mybatis.bean.UserParams;
import org.mybatis.db.DBUtils;
import org.mybatis.db.ProvinceMapper;
import org.mybatis.db.UserMapper;

/**
 * Created by mybatis on 17-1-14.
 */
public class Main {
	

    @Test
    public void test111() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser2(1l);
            System.out.println(user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

    @Test
    public void test01() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            for (int i = 0; i < 3; i++) {
                userMapper.insertUser(new User(null, "u-" + i, "p-" + i,new Date(), "a-" + i));
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void test00() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int i = userMapper.deleteUser(5l);
            System.out.println(i);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

	
    @Test
    public void test1() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> list = userMapper.getUser();
            for (User user : list) {
                System.out.println(user);
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }
    
    @Test
    public void getAll() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> all = userMapper.getAll();
            for (User user : all) {
                System.out.println(user);
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void test2() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setPassword("222222");
            user.setUsername("李四");
            Date regTime = new Date();
            user.setRegTime(regTime);
            userMapper.insertUser(user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

    @Test
    public void test3() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setPassword("111111");
            user.setUsername("张三");
            Date regTime = new Date();
            System.out.println("regTime:" + regTime.getTime());
            user.setRegTime(regTime);
            userMapper.insertUser2(user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

    @Test
    public void test4() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setPassword("111111");
            user.setUsername("张三");
            Date regTime = new Date();
            System.out.println("regTime:" + regTime.getTime());
            user.setRegTime(regTime);
            userMapper.insertUser3(user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

    @Test
    public void test5() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setPassword("111111");
            user.setUsername("张三");
            Date regTime = new Date();
            System.out.println("regTime:" + regTime.getTime());
            user.setRegTime(regTime);
            userMapper.insertUser4(user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

    @Test
    public void test6() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Person p = new Person();
            List<String> list = new ArrayList<String>();
            list.add("足球");
            list.add("排球");
            list.add("音乐");
            list.add("读书");
            p.setInterest(list);
            userMapper.insertPerson(p);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }
    @Test
    public void test7() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Person> people = userMapper.getPerson();
            for (Person person : people) {
                System.out.println(person);
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }
    
    
    @Test
    public void getUserByAddressAndName() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> list = userMapper.getUserByAddressAndName("add", "name");
            for (User user : list) {
                System.out.println(user);
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

    @Test
    public void getUserByAddressAndName2() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, String> map = new HashMap<String, String>();
            map.put("address", "address");
            map.put("username", "name");
            List<User> list = userMapper.getUserByAddressAndName2(map);
            for (User user : list) {
                System.out.println(user);
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }

    @Test
    public void getUserByAddressAndName3() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserParams userParams = new UserParams("name", "add");
            List<User> list = userMapper.getUserByAddressAndName3(userParams);
            for (User user : list) {
                System.out.println(user);
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }
    
    
    @Test
    public void getCount() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int count = userMapper.getCount();
            System.out.println(count);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }
    
    @Test
    public void getProvince() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            ProvinceMapper pm = sqlSession.getMapper(ProvinceMapper.class);
            List<Province> list = pm.getProvince();
            for (Province province : list) {
                System.out.println(province);
            }
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
