package com.wj.store.service;

import com.wj.store.entity.District;

import java.util.List;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/9 10:31
 */
public interface IDistrictService {

    List<District> getByParent(String parent);
    String getNameByCode(String code);
}
