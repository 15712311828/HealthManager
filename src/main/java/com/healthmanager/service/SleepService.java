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
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        criteria.andDateEqualTo(start);

        SleepData sleepData=new SleepData();
        sleepData.setUserId(UserContext.getId());
        sleepData.setDate(start);
        Date date = new Date();
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
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        criteria.andDateEqualTo(start);

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
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        criteria.andDateGreaterThan(start);
        List<SleepData> sleepData = sleepDataMapper.selectByExample(sleepDataExample);
        return sleepData;
    }
}
