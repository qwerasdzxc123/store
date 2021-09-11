package com.wj.store.mapper;

import com.wj.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/8 22:23
 */
public interface AddressMapper {

    /**
     *
     * @param address 收获地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     *
     * @param uid 用户的id
     * @return  当前收获地址总数
     */
    Integer countByUid(Integer uid);

    /**
     *
     * @param uid 用户的id
     * @return 收获地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     *
     * @param uid
     * @return
     */
    Integer updateNonDefaultByUid(Integer uid);

    /**
     *
     * @param aid
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateDefaultByAid(
            @Param("aid") Integer aid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     *
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);

    /**
     *
     * @param aid
     * @return
     */
    Integer deleteByAid(Integer aid);

    /**
     *
     * @param uid
     * @return
     */
    Address findLastModified(Integer uid);
}
