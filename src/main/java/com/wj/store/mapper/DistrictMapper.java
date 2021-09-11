package com.wj.store.mapper;

import com.wj.store.entity.District;

import java.util.List;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/9 9:08
 */
public interface DistrictMapper {
    List<District> findByParent(String parent);

    String findNameByCode(String code);
}
