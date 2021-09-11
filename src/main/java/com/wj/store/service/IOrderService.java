package com.wj.store.service;

import com.wj.store.entity.Order;
import com.wj.store.entity.OrderItem;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/10 22:15
 */
public interface IOrderService {
    Order create(Integer aid, Integer[] cids, Integer uid, String username);
}
