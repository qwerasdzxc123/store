package com.wj.store.mapper;

import com.wj.store.entity.Address;
import com.wj.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/6 19:46
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(20);
        address.setPhone("12345612345");
        address.setName("女朋友");
        Integer rows = addressMapper.insert(address);
        System.out.println("rows=" + rows);
    }

    @Test
    public void countByUid(){
        Integer count = addressMapper.countByUid(20);
        System.out.println(count);
    }


    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(20);
        System.out.println(list);
    }

    @Test
    public void updateNonDefaultByUid() {
        Integer uid = 26;
        Integer rows = addressMapper.updateNonDefaultByUid(uid);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateDefaultByAid() {
        Integer aid = 11;
        String modifiedUser = "管理员";
        Date modifiedTime = new Date();
        Integer rows = addressMapper.updateDefaultByAid(aid, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByAid() {
        Integer aid = 11;
        Address result = addressMapper.findByAid(aid);
        System.out.println(result);
    }

    @Test
    public void deleteByAid() {
        Integer aid = 4;
        Integer rows = addressMapper.deleteByAid(aid);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findLastModified() {
        Integer uid = 19;
        Address result = addressMapper.findLastModified(uid);
        System.out.println(result);
    }

}
