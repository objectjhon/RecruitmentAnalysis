package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.PositionValueMapper;
import com.jhon.recruitmentanalysis.service.PositionValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PositionValueServiceImpl implements PositionValueService {

    @Resource
    PositionValueMapper positionValueMapper;

}
