package com.wj.store.service.impl;

import com.wj.store.entity.Address;
import com.wj.store.entity.Order;
import com.wj.store.entity.OrderItem;
import com.wj.store.mapper.OrderMapper;
import com.wj.store.service.IAddressService;
import com.wj.store.service.ICartService;
import com.wj.store.service.IOrderService;
import com.wj.store.service.ex.InsertException;
import com.wj.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/10 22:16
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Transactional
    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        // 创建当前时间对象
        Date now = new Date();

        // 根据cids查询所勾选的购物车列表中的数据
        List<CartVO> carts = cartService.getVOByCid(uid, cids);

        // 计算这些商品的总价
        long totalPrice = 0;
        for (CartVO cart : carts) {
            totalPrice += cart.getRealPrice() * cart.getNum();
        }

        // 创建订单数据对象
        Order order = new Order();
        // 补全数据：uid
        order.setUid(uid);
        // 查询收货地址数据
        Address address = addressService.getByAid(aid, uid);
        // 补全数据：收货地址相关的6项
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        // 补全数据：totalPrice
        order.setTotalPrice(totalPrice);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(now);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        // 插入订单数据
        Integer rows1 = orderMapper.insertOrder(order);
//        if(1==1)
//            throw new RuntimeException("fsdhfksdhk");
        if (rows1 != 1) {
            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
        }

        // 遍历carts，循环插入订单商品数据
        for (CartVO cart : carts) {
            // 创建订单商品数据
            OrderItem item = new OrderItem();
            // 补全数据：setOid(order.getOid())
            item.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            item.setPid(cart.getPid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getRealPrice());
            item.setNum(cart.getNum());
            // 补全数据：4项日志
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
            // 插入订单商品数据
            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }

        // 返回
        return order;
    }
}