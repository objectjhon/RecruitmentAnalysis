package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.PositionValueMapper;
import com.jhon.recruitmentanalysis.pojo.PositionValue;
import com.jhon.recruitmentanalysis.service.PositionValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionValueServiceImpl implements PositionValueService {

    @Resource
    PositionValueMapper positionValueMapper;

    @Override
    public List<PositionValue> getAllPositionValue(String query) {
        return positionValueMapper.getAllPositionValue(query);
    }

    @Override
    public Integer addPositionValue(PositionValue positionValue) {
        return positionValueMapper.addPositionValue(positionValue);
    }

    @Override
    public PositionValue getPositionValueById(Integer id) {
        return positionValueMapper.getPositionValueById(id);
    }

    @Override
    public Integer updatePositionValue(PositionValue positionValue) {
        return positionValueMapper.updatePositionValue(positionValue);
    }

    @Override
    public Integer updatePositionStatus(Integer id, Integer status) {
        return positionValueMapper.updatePositionStatus(id,status);
    }

    @Override
    public Integer deletePositionValue(Integer id) {
        return positionValueMapper.deletePositionValue(id);
    }
}
