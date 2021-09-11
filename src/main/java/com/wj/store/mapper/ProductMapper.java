package com.wj.store.mapper;

import com.wj.store.entity.Product;

import java.util.*;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/9 22:08
 */
public interface ProductMapper {
    List<Product> findHotList();
    Product findById(Integer id);
}
