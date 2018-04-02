package com.healthmanager.service;

import com.github.pagehelper.PageHelper;
import com.healthmanager.common.UserContext;
import com.healthmanager.dao.WaterDataMapper;
import com.healthmanager.model.WaterData;
import com.healthmanager.model.WaterDataExample;
import com.healthmanager.param.WaterAddParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WaterService {

    @Resource
    private WaterDataMapper waterDataMapper;

    public void add(WaterAddParam waterAddParam){
        waterDataMapper.insert(waterAddParam.toWaterData());
    }

    public List<WaterData> query(){
        WaterDataExample waterDataExample=new WaterDataExample();
        WaterDataExample.Criteria criteria = waterDataExample.createCriteria();
        criteria.andUserIdEqualTo(UserContext.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        criteria.andTimeGreaterThanOrEqualTo(start);
        List<WaterData> waterData = waterDataMapper.selectByExample(waterDataExample);
        return waterData;
    }
}
