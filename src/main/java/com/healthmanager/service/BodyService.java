package com.healthmanager.service;

import com.healthmanager.common.UserContext;
import com.healthmanager.dao.BodyDataMapper;
import com.healthmanager.model.BodyData;
import com.healthmanager.model.BodyDataExample;
import com.healthmanager.param.BodyUpdateParam;
import com.healthmanager.util.ValidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BodyService {

    @Resource
    private BodyDataMapper bodyDataMapper;

    public void update(BodyUpdateParam bodyUpdateParam){
        BodyDataExample bodyDataExample=new BodyDataExample();
        BodyDataExample.Criteria criteria = bodyDataExample.createCriteria();
        criteria.andUseridEqualTo(UserContext.getId());
        Long result=bodyDataMapper.countByExample(bodyDataExample);

        if(result==0){
            bodyDataMapper.insert(bodyUpdateParam.toBodyData());
        }
        else{
            bodyDataMapper.updateByExample(bodyUpdateParam.toBodyData(),bodyDataExample);
        }
    }

    public BodyData query(){
        BodyDataExample bodyDataExample=new BodyDataExample();
        BodyDataExample.Criteria criteria = bodyDataExample.createCriteria();
        criteria.andUseridEqualTo(UserContext.getId());
        List<BodyData> bodyDatas = bodyDataMapper.selectByExample(bodyDataExample);

        ValidUtil.checkExist(bodyDatas,"身体信息未完善");

        return bodyDatas.get(0);
    }
}
