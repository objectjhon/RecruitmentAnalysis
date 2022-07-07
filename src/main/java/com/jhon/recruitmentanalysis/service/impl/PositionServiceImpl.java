package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.PositionMapper;
import com.jhon.recruitmentanalysis.pojo.Position;
import com.jhon.recruitmentanalysis.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class PositionServiceImpl implements PositionService {

    @Resource
    PositionMapper positionMapper;

    @Override
    public List<Position> getAll(String year, String month,
                                 String day, String city,
                                 BigDecimal salaryMin, BigDecimal salaryMax) {
        return positionMapper.getAll(year,month,day,city,salaryMin,salaryMax);
    }

    @Override
    public List<Position> getAll(String year, String month, String day, String city, BigDecimal salaryMin, BigDecimal salaryMax, List<String> keyword) {
        return positionMapper.getAllByKeyWord(year,month,day,city,salaryMin,salaryMax,keyword);
    }

    @Override
    public List<Map<String,Object>> getCountByPositionDate(List<String> position, List<String> city) {
        return positionMapper.getCountByPositionDate(position,city);
    }

    @Override
    public List<String> getAllEducation() {
        return positionMapper.getAllEducation();
    }

    @Override
    public List<Map> getPositionEducation(List<String> position) {
        return positionMapper.getPositionEducation(position);
    }

    @Override
    public List<String> getAllExperience() {
        return positionMapper.getAllExperience();
    }

    @Override
    public List<Map> getPositionExperience(List<String> position) {
        return positionMapper.getPositionExperience(position);
    }

    @Override
    public List<Map> getCityByPosition(List<String> position) {
        return positionMapper.getCityByPosition(position);
    }

}
