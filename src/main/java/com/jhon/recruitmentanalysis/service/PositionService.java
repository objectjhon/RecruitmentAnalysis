package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.Position;

import java.math.BigDecimal;
import java.util.List;

public interface PositionService {

    List<Position> getAll(String year, String month,
                          String day, String city,
                          BigDecimal salaryMin,BigDecimal salaryMax);

    List<Position> getAll(String year, String month,
                          String day, String city,
                          BigDecimal salaryMin,BigDecimal salaryMax,
                          List<String> keyword);

}
