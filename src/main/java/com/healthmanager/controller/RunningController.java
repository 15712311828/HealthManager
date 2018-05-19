package com.healthmanager.controller;

import com.healthmanager.common.JsonResult;
import com.healthmanager.param.RunningAddParam;
import com.healthmanager.service.RunningService;
import com.healthmanager.util.ValidUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/running")
public class RunningController {

    @Resource
    RunningService runningService;

    @RequestMapping("/add")
    public JsonResult add(@RequestBody RunningAddParam param){
        ValidUtil.checkLogin();
        runningService.add(param);
        return JsonResult.success();
    }

    @RequestMapping("/getToday")
    public JsonResult getToday(){
        ValidUtil.checkLogin();
        int step = runningService.getToday();
        return JsonResult.success(step);
    }
}
