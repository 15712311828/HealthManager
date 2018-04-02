package com.healthmanager.controller;


import com.healthmanager.common.JsonResult;
import com.healthmanager.model.WaterData;
import com.healthmanager.param.PageQueryParam;
import com.healthmanager.param.WaterAddParam;
import com.healthmanager.service.WaterService;
import com.healthmanager.util.ValidUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/water")
public class WaterController {

    @Resource
    private WaterService waterService;

    @RequestMapping("/add")
    public JsonResult add(@RequestBody @Valid WaterAddParam param){
        ValidUtil.checkLogin();
        waterService.add(param);
        return JsonResult.success();
    }

    @RequestMapping("/query")
    public JsonResult query(){
        ValidUtil.checkLogin();
        List<WaterData> query = waterService.query();
        return JsonResult.success(query);
    }
}
