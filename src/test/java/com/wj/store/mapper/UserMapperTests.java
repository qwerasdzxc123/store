package com.wj.store.mapper;

import com.wj.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/6 19:46
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("wangjingwj");
        user.setPassword("123456");
        user.setPhone("123456789");
        user.setEmail("1193216545@qq.com");
        Integer rows = userMapper.insert(user);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByUsername() {
        String username = "user01";
        User result = userMapper.findByUsername(username);
        System.out.println(result);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(20,"321",
                "管理员",new Date());
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(20));
    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(20);
        user.setPhone("17858802222");
        user.setEmail("aaaaa@cy.com");
        user.setGender(1);
        user.setModifiedUser("系统管理员");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateAvatarByUid() {
        Integer uid = 20;
        String avatar = "/upload/avatar.png";
        String modifiedUser = "超级管理员";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }



}
