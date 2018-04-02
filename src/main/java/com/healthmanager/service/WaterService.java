package com.healthmanager.service;

import com.healthmanager.dao.WaterDataMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WaterService {

    @Resource
    private WaterDataMapper waterDataMapper;

    public void add(){

    }
}
