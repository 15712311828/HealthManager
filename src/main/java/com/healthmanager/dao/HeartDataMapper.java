package com.healthmanager.dao;

import com.healthmanager.model.HeartData;
import com.healthmanager.model.HeartDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HeartDataMapper {
    long countByExample(HeartDataExample example);

    int deleteByExample(HeartDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HeartData record);

    int insertSelective(HeartData record);

    List<HeartData> selectByExample(HeartDataExample example);

    HeartData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HeartData record, @Param("example") HeartDataExample example);

    int updateByExample(@Param("record") HeartData record, @Param("example") HeartDataExample example);

    int updateByPrimaryKeySelective(HeartData record);

    int updateByPrimaryKey(HeartData record);
}