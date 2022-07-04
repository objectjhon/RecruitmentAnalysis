package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.Position;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PositionService {

    List<Position> getAll(String year, String month,
                          String day, String city,
                          BigDecimal salaryMin,BigDecimal salaryMax);

    List<Position> getAll(String year, String month,
                          String day, String city,
                          BigDecimal salaryMin,BigDecimal salaryMax,
                          List<String> keyword);

    List<Map<String,Object>> getCountByPositionDate(List<String> position,List<String> city);

}
