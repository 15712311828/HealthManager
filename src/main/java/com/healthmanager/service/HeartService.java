package com.healthmanager.service;


import com.healthmanager.common.UserContext;
import com.healthmanager.dao.HeartDataMapper;
import com.healthmanager.model.HeartData;
import com.healthmanager.model.HeartDataExample;
import com.healthmanager.param.HeartUpdateParam;
import com.healthmanager.util.ValidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HeartService {

    @Resource
    private HeartDataMapper heartDataMapper;

    public void update(HeartUpdateParam heartUpdateParam){
        HeartDataExample heartDataExample=new HeartDataExample();
        HeartDataExample.Criteria criteria = heartDataExample.createCriteria();
        criteria.andUserIdEqualTo(UserContext.getId());
        Long result = heartDataMapper.countByExample(heartDataExample);

        if(result==0){
            heartDataMapper.insert(heartUpdateParam.toHeartData());
        }
        else{
            heartDataMapper.updateByExampleSelective(heartUpdateParam.toHeartData(),heartDataExample);
        }
    }

    public HeartData query(){
        HeartDataExample heartDataExample=new HeartDataExample();
        HeartDataExample.Criteria criteria = heartDataExample.createCriteria();
        criteria.andUserIdEqualTo(UserContext.getId());
        List<HeartData> heartDatas = heartDataMapper.selectByExample(heartDataExample);
        ValidUtil.checkExist(heartDatas,"暂无数据");
        return heartDatas.get(0);
    }
}
