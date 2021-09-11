package com.wj.store.mapper;

import com.wj.store.entity.Cart;
import com.wj.store.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.*;
import java.util.Date;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/10 11:02
 */
public interface CartMapper {
    Integer insert(Cart cart);
    Integer updateNumByCid(@Param("cid") Integer cid,
                           @Param("num") Integer num,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime);
    Cart findByUidAndPid(@Param("uid") Integer uid,@Param("pid") Integer pid);

    List<CartVO> findByVOUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVO> findVOByCid(Integer[] cids);
}
