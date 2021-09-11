package com.wj.store.mapper;

import com.wj.store.entity.User;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Date;

/**
 * @author: WangJin
 * @description   user 用户的数据
 * @return 受影响的行数
 * @date: 2021/9/6 19:12
 * @MapperScan 注解当前项目的路径和的位置
 */




public interface UserMapper {
    Integer insert(User user);
    User findByUsername(String username);
    /**
     * 根据uid更新用户的密码
     * @param uid 用户的id
     * @param password 新密码
     * @param modifiedUser 最后修改执行人
     * @param modifiedTime 最后修改时间
     * @return 受影响的行数
     * 用注解来简化xml配置时，@Param注解的作用是给参数命名，参数命名后就能根据名字得到参数
     * 值，正确的将参数传入sql语句中。@Param("参数名")注解中的参数名需要和sql语句中的#{参数
     * 名}的参数名保持一致。
     */
    Integer updatePasswordByUid(@Param("uid") Integer uid,
                                @Param("password") String password,
                                @Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime);
    User findByUid(Integer uid);

    Integer updateInfoByUid(User user);

    Integer updateAvatarByUid(
                    @Param("uid") Integer uid,
                    @Param("avatar") String avatar,
                    @Param("modifyUser") String modifyUser,
                    @Param("modifyTime") Date modifyTime);

}
