package com.wj.store.mapper;

import com.wj.store.entity.Order;
import com.wj.store.entity.OrderItem;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/10 21:46
 */
public interface OrderMapper {
    Integer insertOrder(Order order);
    Integer insertOrderItem(OrderItem orderItem);
}
