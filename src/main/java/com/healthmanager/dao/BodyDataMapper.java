package com.healthmanager.dao;

import com.healthmanager.model.BodyData;
import com.healthmanager.model.BodyDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BodyDataMapper {
    long countByExample(BodyDataExample example);

    int deleteByExample(BodyDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BodyData record);

    int insertSelective(BodyData record);

    List<BodyData> selectByExample(BodyDataExample example);

    BodyData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BodyData record, @Param("example") BodyDataExample example);

    int updateByExample(@Param("record") BodyData record, @Param("example") BodyDataExample example);

    int updateByPrimaryKeySelective(BodyData record);

    int updateByPrimaryKey(BodyData record);
}