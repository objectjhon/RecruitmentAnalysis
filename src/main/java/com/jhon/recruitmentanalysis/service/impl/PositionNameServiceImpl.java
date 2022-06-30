package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.PositionNameMapper;
import com.jhon.recruitmentanalysis.pojo.PositionName;
import com.jhon.recruitmentanalysis.service.PositionNameService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PositionNameServiceImpl implements PositionNameService {

    @Resource
    PositionNameMapper positionNameMapper;

    @Override
    public List<PositionName> getAllPositionName() {
        return positionNameMapper.getAllPositionName();
    }

    @Override
    public List<PositionName> getAllPositionName(List<String> city) {
        return positionNameMapper.getAllPositionNameByCity(city);
    }

    @Override
    public PositionName getHighestPositionCount(String year,String month) {
        return positionNameMapper.getHighestPositionCount(year, month);
    }

    @Override
    public PositionName getHighestPositionCount(String year,String month,List<String> city) {
        return positionNameMapper.getHighestPositionCountByCity(year, month, city);
    }

    @Override
    public Map<String, Object> getHighestCityPosition(String year,String month) {
        return positionNameMapper.getHighestCityPosition(year, month);
    }

    @Override
    public Map<String, Object> getHighestCityPosition(String year,String month,List<String> city) {
        return positionNameMapper.getHighestCityPositionByCity(year, month, city);
    }

    @Override
    public List<Map<String, Object>> getCityPosition() {
        return positionNameMapper.getCityPosition();
    }

    @Override
    public List<Map<String, Object>> getCityPosition(List<String> city) {
        return positionNameMapper.getCityPositionByCity(city);
    }

    @Override
    public List<Map<String,Object>> getAllPositionCount(Integer limit) {
        return positionNameMapper.getAllPositionCount(limit);
    }

    @Override
    public List<Map<String,Object>> getAllPositionCount(Integer limit, List<String> city) {
        return positionNameMapper.getAllPositionCountByCity(limit,city);
    }

    @Override
    public List<Map<String, Object>> getPositionCityByPosition(String position) {
        return positionNameMapper.getPositionCityByPosition(position);
    }

    @Override
    public List<Map<String, Object>> getPositionCityByPosition(String position, List<String> city) {
        return positionNameMapper.getPositionCityByPositionCity(position,city);
    }

    @Override
    public List<String> getAllPosition() {
        return positionNameMapper.getAllPosition();
    }

}
