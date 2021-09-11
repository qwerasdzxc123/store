package com.wj.store.service.impl;

import com.wj.store.entity.District;
import com.wj.store.mapper.DistrictMapper;
import com.wj.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/9 10:32
 */
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);

        for (District d : list){
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        String name = districtMapper.findNameByCode(code);
        return name;
    }
}
