package com.wj.store.service;

import com.wj.store.entity.Product;

import java.util.*;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/9 22:33
 */
public interface IProductService {
    List<Product> findHotList();
    Product findById(Integer id);
}
