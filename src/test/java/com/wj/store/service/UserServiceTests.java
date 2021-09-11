package com.wj.store.service;

import com.wj.store.entity.User;
import com.wj.store.mapper.UserMapper;
import com.wj.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/6 19:46
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    public void reg() {

        try {
            User user = new User();
            user.setUsername("qwer");
            user.setPassword("123456");
            userService.reg(user);
            System.out.println("ok");
        } catch (SecurityException e) {
            //获取类的对象，在获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常的具体描述信息
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void login() {
        try {
            String username = "qwer";
            String password = "888888";
            User user = userService.login(username, password);
            System.out.println("登录成功！" + user);
        } catch (ServiceException e) {
            System.out.println("登录失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void changePassword() {
        try {
            Integer uid = 34;
            String username = "qwer";
            String oldPassword = "123456";
            String newPassword = "888888";
            userService.changePassword(uid, username, oldPassword, newPassword);
            System.out.println("密码修改成功！");
        } catch (ServiceException e) {
            System.out.println("密码修改失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getByUid() {
        try {
            Integer uid = 20;
            User user = userService.getByUid(uid);
            System.out.println(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void changeInfo() {
        try {
            Integer uid = 20;
            String username = "数据管理员";
            User user = new User();
            user.setPhone("15512328888");
            user.setEmail("admin03@cy.cn");
            user.setGender(2);
            userService.changeInfo(uid, username, user);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(22,"/upload/test.png","小明");

    }

}