package com.wj.store.service.impl;

import com.wj.store.entity.Cart;
import com.wj.store.entity.Product;
import com.wj.store.mapper.CartMapper;
import com.wj.store.service.ICartService;
import com.wj.store.service.IProductService;
import com.wj.store.service.ex.AccessDeniedException;
import com.wj.store.service.ex.CartNotFoundException;
import com.wj.store.service.ex.InsertException;
import com.wj.store.service.ex.UpdateException;
import com.wj.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/10 11:42
 */
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IProductService productService;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        // 根据参数pid和uid查询购物车中的数据
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date =  new Date();
        // 判断查询结果是否为null
        if (result == null){  // 是：表示该用户并未将该商品添加到购物车
            //创建cart对象
            Cart cart = new Cart();
            //补全数据
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            //价格：来来自于商品中的数据
            Product product = productService.findById(pid);
            cart.setPrice(product.getPrice());
            //补全4日志
            cart.setCreatedUser(username);
            cart.setModifiedTime(date);

            //执行输入的操作
            Integer rows = cartMapper.insert(cart);
            if (rows != 1){
                throw new InsertException("插入数据时产生未知异常");
            }
        }else {
            //表示当时商品在购物车中已存在，则更新这条数据的num值
            Integer num = result.getNum() + amount;
            Integer rows = cartMapper.updateNumByCid(result.getCid(), num, username, date);
            if(rows != 1){
                throw new UpdateException("更新数据是产生未知异常");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findByVOUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if(result == null){  // 是：抛出CartNotFoundException
            throw new CartNotFoundException("用户的数据库不存在");
        }
        // 判断查询结果中的uid与参数uid是否不一致
        if(!result.getUid().equals(uid)){ // 是：抛出AccessDeniedException
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid,num,username,new Date());
        if(rows != 1){
            throw new UpdateException("数据更新失败");
        }
        //返回新的购物车数据的总量
        return num;
    }

    @Override
    public Integer deleteNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if(result == null){  // 是：抛出CartNotFoundException
            throw new CartNotFoundException("用户的数据库不存在");
        }
        // 判断查询结果中的uid与参数uid是否不一致
        if(!result.getUid().equals(uid)){ // 是：抛出AccessDeniedException
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() - 1;
        Integer rows = cartMapper.updateNumByCid(cid,num,username,new Date());
        if(rows != 1){
            throw new UpdateException("数据更新失败");
        }
        //返回新的购物车数据的总量
        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        /**
         for (CartVO cart : list) {
         if (!cart.getUid().equals(uid)) {
         list.remove(cart);
         }
         }
         */
        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()) {
            CartVO cart = it.next();
            if (!cart.getUid().equals(uid)) {
                it.remove();
            }
        }
        return list;
    }

}
