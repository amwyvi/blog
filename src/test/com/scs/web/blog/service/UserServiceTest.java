package com.scs.web.blog.service;

import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.util.Result;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserServiceTest {
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Test
    public void signIn() {
        UserDto userDao = new UserDto();
        userDao.setMobile("13913211111");
        userDao.setPassword("698d51a19d8a121ce581499d7b701668");
        Result result = userService.signIn(userDao);
        System.out.println("code:" + result.getCode() + "," + "msg:" + result.getMsg());
    }

    @Test
    public void getHotUsers() {
        Result result = userService.getHotUsers();
        System.out.println(result);
    }

    @Test
    public void checkMobile() {
        Result result = userService.checkMobile("13951905172");
        System.out.println(result);
    }

    @Test
    public void signUp() {
        UserDto userDto = new UserDto();
        userDto.setMobile("13911112122");
        userDto.setPassword("111");

        Result result = userService.signUp(userDto);
        System.out.println(result);
    }

    @Test
    public void delete() {

        userService.delete((long) 20);
    }

    @Test
    public void changeUser() {
        Result result = new Result();
        result= userService.getUser(51);
        System.out.println(result.toString());
    }
}