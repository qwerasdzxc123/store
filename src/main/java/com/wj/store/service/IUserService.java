package com.wj.store.service;

import com.wj.store.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/6 20:49
 */
public interface IUserService {
    void reg(User user);
    User login(String username,String password);
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);
    /**
     * 获取当前登录的用户的信息
     * @param uid 当前登录的用户的id
     * @return 当前登录的用户的信息
     */
    User getByUid(Integer uid);
    /**
     * 修改用户资料
     * @param user 用户的新的数据
     */
//    void changeInfo(User user, HttpSession httpSession);
      void changeInfo(Integer uid, String username, User user);

    /**
     * 更改用户的头像
     * @param uid 用户的id
     * @param avatar 用户头像的路径
     * @param username 用户的名称
     */
      void changeAvatar(Integer uid,
                        String avatar,
                        String username);

}
