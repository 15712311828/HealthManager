package com.healthmanager.service;

import com.github.pagehelper.PageHelper;
import com.healthmanager.common.UserContext;
import com.healthmanager.dao.SleepDataMapper;
import com.healthmanager.exception.BusinessException;
import com.healthmanager.model.SleepData;
import com.healthmanager.model.SleepDataExample;
import com.healthmanager.model.WaterData;
import com.healthmanager.util.ValidUtil;
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
        SleepData sleepData=new SleepData();
        sleepData.setUserId(UserContext.getId());
        Date date=new Date();
        sleepData.setStartTime(date);
        sleepData.setEndTime(date);

        sleepDataMapper.insert(sleepData);
    }

    public void endSleep(){
        SleepDataExample sleepDataExample=new SleepDataExample();
        SleepDataExample.Criteria criteria = sleepDataExample.createCriteria();
        criteria.andUserIdEqualTo(UserContext.getId());
        PageHelper.startPage(1,1);
        PageHelper.orderBy("start_time desc");
        List<SleepData> sleepDatas = sleepDataMapper.selectByExample(sleepDataExample);
        ValidUtil.checkExist(sleepDatas,"没有点击开始");
        if(!sleepDatas.get(0).getStartTime().equals(sleepDatas.get(0).getEndTime())){
            throw new BusinessException("没有点击开始");
        }
        criteria.andStartTimeEqualTo(sleepDatas.get(0).getStartTime());

        SleepData sleepData=new SleepData();
        sleepData.setEndTime(new Date());

        sleepDataMapper.updateByExampleSelective(sleepData,sleepDataExample);

    }

    public List<SleepData> query(){
        SleepDataExample sleepDataExample=new SleepDataExample();
        SleepDataExample.Criteria criteria = sleepDataExample.createCriteria();
        criteria.andUserIdEqualTo(UserContext.getId());
        PageHelper.startPage(1,5);
        PageHelper.orderBy("start_time desc");
        List<SleepData> sleepData = sleepDataMapper.selectByExample(sleepDataExample);
        return sleepData;
    }
}
