package com.healthmanager.controller;

import com.healthmanager.common.JsonResult;
import com.healthmanager.model.HeartData;
import com.healthmanager.param.HeartUpdateParam;
import com.healthmanager.service.HeartService;
import com.healthmanager.util.ValidUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RequestMapping("/heart")
@RestController
public class HeartController {

    @Resource
    private HeartService heartService;

    @RequestMapping("/update")
    public JsonResult update(@RequestBody @Valid HeartUpdateParam heartUpdateParam){
        ValidUtil.checkLogin();
        heartService.update(heartUpdateParam);
        return JsonResult.success();
    }

    @RequestMapping("/query")
    public JsonResult query(){
        ValidUtil.checkLogin();
        HeartData query = heartService.query();
        return JsonResult.success(query);
    }
}
