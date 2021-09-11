package com.wj.store.service;

import com.wj.store.entity.Cart;
import com.wj.store.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.*;
import java.util.Date;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/10 11:38
 */
public interface ICartService {
    void addToCart(Integer uid, Integer pid, Integer amount, String username);
    List<CartVO> getVOByUid(Integer uid);
    Integer addNum(Integer cid, Integer uid, String username);
    Integer deleteNum(Integer cid,Integer uid ,String username);
    List<CartVO> getVOByCid(Integer uid, Integer[] cids);
}
