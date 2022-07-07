package com.jhon.recruitmentanalysis.service;

import com.jhon.recruitmentanalysis.pojo.PositionSalary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PositionSalaryService {

    List<PositionSalary> getAllPositionSalary(String position);

    List<PositionSalary> getAllPositionSalary(String position,List<String> city);

    List<Map<String,Object>> getPositionCount(String year,String month);

    List<Map<String,Object>> getPositionCount(String year,String month,List<String> city);

    List<Map<String,Object>> getPositionSalary();

    List<Map<String,Object>> getPositionSalary(List<String> city);

    Map<String,Object> getHighestSalaryPosition(String year,String month);

    Map<String,Object> getHighestSalaryPositionByCity(String year,String month,List<String> city);

    List<Map> getPositionBySalary(BigDecimal minSalary,BigDecimal maxSalary);

}
