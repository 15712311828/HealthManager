package com.healthmanager.controller;


import com.healthmanager.common.JsonResult;
import com.healthmanager.model.SleepData;
import com.healthmanager.service.SleepService;
import com.healthmanager.util.ValidUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sleep")
public class SleepController {

    @Resource
    private SleepService sleepService;

    @RequestMapping("/start")
    public JsonResult start(){
        ValidUtil.checkLogin();
        sleepService.startSleep();
        return JsonResult.success();
    }

    @RequestMapping("/end")
    public JsonResult end(){
        ValidUtil.checkLogin();
        sleepService.endSleep();
        return JsonResult.success();
    }

    @RequestMapping("/query")
    public JsonResult query(){
        ValidUtil.checkLogin();
        List<SleepData> query = sleepService.query();
        return JsonResult.success(query);
    }
}
