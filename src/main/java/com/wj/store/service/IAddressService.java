package com.wj.store.service;

import com.wj.store.entity.Address;
import java.util.List;

/**
 * @author: WangJin
 * @description 收货地址物业接口层
 * @date: 2021/9/8 22:48
 */
public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    void setDefault(Integer aid,Integer uid ,String username);

    void delete(Integer aid, Integer uid, String username);

    Address getByAid(Integer aid,Integer uid);
}
