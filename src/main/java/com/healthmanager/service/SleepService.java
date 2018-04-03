package com.healthmanager.service;

import com.healthmanager.common.UserContext;
import com.healthmanager.dao.SleepDataMapper;
import com.healthmanager.exception.BusinessException;
import com.healthmanager.model.SleepData;
import com.healthmanager.model.SleepDataExample;
import com.healthmanager.model.WaterData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SleepService {

    @Resource
    private SleepDataMapper sleepDataMapper;

    public void startSleep(){
        SleepDataExample sleepDataExample=new SleepDataExample();
        SleepDataExample.Criteria criteria = sleepDataExample.createCriteria();
        criteria.andUserIdEqualTo(UserContext.getId());
        Date date = new Date();
        Long day=date.getTime()/(24*60*60*1000);
        criteria.andDayEqualTo(day);

        SleepData sleepData=new SleepData();
        sleepData.setUserId(UserContext.getId());
        sleepData.setDay(day);
        sleepData.setStartTime(date);
        sleepData.setEndTime(date);

        Long result=sleepDataMapper.countByExample(sleepDataExample);
        if(result>0){
            sleepDataMapper.updateByExampleSelective(sleepData,sleepDataExample);
        }
        else{
            sleepDataMapper.insert(sleepData);
        }
    }

    public void endSleep(){
        SleepDataExample sleepDataExample=new SleepDataExample();
        SleepDataExample.Criteria criteria = sleepDataExample.createCriteria();
        criteria.andUserIdEqualTo(UserContext.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,   -1);
        Date date = calendar.getTime();
        Long day=date.getTime()/(24*60*60*1000);
        criteria.andDayEqualTo(day);

        SleepData sleepData=new SleepData();
        sleepData.setEndTime(new Date());

        Long result=sleepDataMapper.countByExample(sleepDataExample);
        if(result>0){
            sleepDataMapper.updateByExampleSelective(sleepData,sleepDataExample);
        }
        else{
            throw new BusinessException("昨日没有点击开始");
        }
    }

    public List<SleepData> query(){
        SleepDataExample sleepDataExample=new SleepDataExample();
        SleepDataExample.Criteria criteria = sleepDataExample.createCriteria();
        criteria.andUserIdEqualTo(UserContext.getId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,   -5);
        Date date = calendar.getTime();
        Long day=date.getTime()/(24*60*60*1000);
        criteria.andDayGreaterThan(day);
        List<SleepData> sleepData = sleepDataMapper.selectByExample(sleepDataExample);
        return sleepData;
    }
}
