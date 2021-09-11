package com.wj.store.controller;

import com.wj.store.entity.District;
import com.wj.store.service.IDistrictService;
import com.wj.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: WangJin
 * @description
 * @date: 2021/9/9 10:53
 */
@RequestMapping("districts")
@RestController
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService districtService;

    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(OK,data);
    }
}
