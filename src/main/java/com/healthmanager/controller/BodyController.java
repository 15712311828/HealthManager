package com.healthmanager.controller;

import com.healthmanager.common.JsonResult;
import com.healthmanager.model.BodyData;
import com.healthmanager.param.BodyUpdateParam;
import com.healthmanager.service.BodyService;
import com.healthmanager.util.ValidUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RequestMapping("/body")
@RestController
public class BodyController {

    @Resource
    private BodyService bodyService;

    @RequestMapping("/update")
    public JsonResult update(@RequestBody @Valid BodyUpdateParam bodyUpdateParam){
        ValidUtil.checkLogin();
        bodyService.update(bodyUpdateParam);
        return JsonResult.success();
    }

    @RequestMapping("/query")
    public JsonResult query(){
        ValidUtil.checkLogin();
        BodyData query = bodyService.query();
        return JsonResult.success(query);
    }
}
