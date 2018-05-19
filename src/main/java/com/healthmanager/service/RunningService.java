package com.healthmanager.service;

import com.github.pagehelper.PageHelper;
import com.healthmanager.common.UserContext;
import com.healthmanager.dao.RunningDataMapper;
import com.healthmanager.model.RunningData;
import com.healthmanager.model.RunningDataExample;
import com.healthmanager.param.RunningAddParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RunningService {

    @Resource
    private RunningDataMapper runningDataMapper;

    public void add(RunningAddParam param){
        runningDataMapper.insert(param.toRunningData());
    }

    public int getToday(){

        RunningDataExample example=new RunningDataExample();
        RunningDataExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(UserContext.getId());
        criteria.andTimeEqualTo(new Date());
        List<RunningData> runningData = runningDataMapper.selectByExample(example);
        if(runningData.isEmpty()){
            return 0;
        }
        int step=0;
        int last=runningData.get(0).getStep();
        for(int i=1;i<runningData.size();i++){
            if(runningData.get(i).getStep()<last){
                step+=runningData.get(i-1).getStep()-last;
                last=runningData.get(i).getStep();
            }
        }
        step+=runningData.get(runningData.size()-1).getStep()-last;
        return step;
    }
}
