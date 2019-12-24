package com.scs.web.blog.dao;

import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.domain.vo.UserVo;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.SpiderUtil;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserDaoTest {
    private UserDao userDao = DaoFactory.getUserDaoInstance();

    @Test
    public void insert() throws SQLException {
        User user = new User();
        user.setMobile("13988887773");
        user.setPassword("111");
        userDao.insert(user);
    }

    @Test
    public void batchInsert() throws SQLException {
        userDao.batchInsert(SpiderUtil.getUsers());
    }

    @Test
    public void findUserByMobile() throws SQLException {
        User user = userDao.findUserByMobile("13921557438");
        System.out.println(user);
    }

    @Test
    public void selectHotUsers() throws SQLException {
        List<User> userList = userDao.selectHotUsers();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByKeywords() throws SQLException{
        List<User> userList = userDao.selectByKeywords("王");
        System.out.println(userList.size());
    }

    @Test
    public void deleteUserById() throws SQLException {
        userDao.deleteUserById((long) 24);
    }

    @Test
    public void changeUser() throws SQLException {
        User user=new User();
        user.setAddress("南京");
        user.setNickname("喜羊羊");
        user.setGender("男");
        user.setIntroduction("nv");
        user.setId((long) 1);
//        System.out.println("成功");
        int n=userDao.changeUser(user);
        System.out.println(n);


    }
}