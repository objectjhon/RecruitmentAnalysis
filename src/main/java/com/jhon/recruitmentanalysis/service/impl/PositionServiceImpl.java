package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.PositionMapper;
import com.jhon.recruitmentanalysis.pojo.Position;
import com.jhon.recruitmentanalysis.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

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

}