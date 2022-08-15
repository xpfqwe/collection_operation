package com.yjxxt;

import com.yjxxt.pojo.User;
import com.yjxxt.service.UserService;
import org.junit.Before;
import org.junit.Test;


public class AppTest {

    private UserService userService = null;

    @Before
    public void init(){
        System.out.println("方法执行前.....");
        userService = new UserService();
    }

    //用户登陆
    @Test
    public void loginUser(){
        userService.loginUser("admin","123456");
    }

    //添加用户信息
    @Test
    public void addUser(){
        userService.addUser(new User(3,"lisi","123456","lisi","",""));
        userService.listUser();
    }

    //查询用户信息
    @Test
    public void listUser(){
        userService.listUser();
    }

    //用户信息更新
    @Test
    public void updateUser(){
        userService.listUser();
        System.out.println("--------------------------------");
        userService.updateUser(new User(2,"lisi","123456","lisi","",""));
        userService.listUser();

    }

    //用户信息删除
    @Test
    public void delUser(){
        userService.delUser(1);
        System.out.println("删除数据后...");
        userService.listUser();
    }
}
