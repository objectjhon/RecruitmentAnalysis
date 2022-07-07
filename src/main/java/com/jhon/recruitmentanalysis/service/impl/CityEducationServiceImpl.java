package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.CityEducationMapper;
import com.jhon.recruitmentanalysis.service.CityEducationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class CityEducationServiceImpl implements CityEducationService {

    @Resource
    CityEducationMapper cityEducationMapper;

    @Override
    public List<Map> getCityEducation() {
        return cityEducationMapper.getCityEducation();
    }

    @Override
    public List<Map> getCityEducation(List<String> city) {
        return cityEducationMapper.getCityEducationByCity(city);
    }

    @Override
    public List<Map> getEducationBySalary(BigDecimal minSalary, BigDecimal maxSalary) {
        return cityEducationMapper.getEducationBySalary(minSalary,maxSalary);
    }

    @Override
    public List<Map> getCityBySalary(BigDecimal minSalary, BigDecimal maxSalary) {
        return cityEducationMapper.getCityBySalary(minSalary,maxSalary);
    }

    @Override
    public List<Map> getPositionNumByCity() {
        return cityEducationMapper.getPositionNumByCity();
    }

    @Override
    public List<Map> getPositionNumByCity(List<String> city) {
        return cityEducationMapper.getPositionNumByCityList(city);
    }
}
