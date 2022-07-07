package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.PositionSalaryMapper;
import com.jhon.recruitmentanalysis.pojo.PositionSalary;
import com.jhon.recruitmentanalysis.service.PositionSalaryService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class PositionSalaryServiceImpl implements PositionSalaryService {

    @Resource
    PositionSalaryMapper positionSalaryMapper;

    @Override
    public List<PositionSalary> getAllPositionSalary(String position) {
        return positionSalaryMapper.getAllPositionSalary(position);
    }

    @Override
    public List<PositionSalary> getAllPositionSalary(String position,List<String> city) {
        return positionSalaryMapper.getAllPositionSalaryByCity(position,city);
    }

    @Override
    public List<Map<String,Object>> getPositionCount(String year,String month) {
        return positionSalaryMapper.getPositionCount(year,month);
    }

    @Override
    public List<Map<String,Object>> getPositionCount(String year,String month, List<String> city) {
        return positionSalaryMapper.getPositionCountByCity(year,month,city);
    }

    @Override
    public List<Map<String, Object>> getPositionSalary() {
        return positionSalaryMapper.getPositionSalary();
    }

    @Override
    public List<Map<String, Object>> getPositionSalary(List<String> city) {
        return positionSalaryMapper.getPositionSalaryByCity(city);
    }

    @Override
    public Map<String, Object> getHighestSalaryPosition(String year,String month) {
        return positionSalaryMapper.getHighestSalaryPosition(year,month);
    }

    @Override
    public Map<String, Object> getHighestSalaryPositionByCity(String year,String month,List<String> city) {
        return positionSalaryMapper.getHighestSalaryPositionByCity(year,month,city);
    }

    @Override
    public List<Map> getPositionBySalary(BigDecimal minSalary, BigDecimal maxSalary) {
        return positionSalaryMapper.getPositionBySalary(minSalary,maxSalary);
    }

}
