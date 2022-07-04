package com.jhon.recruitmentanalysis.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PositionKeyService {

    List<Map<String,Object>> getAllKeyValue(List<String> city);

    List<Map<String,Object>> getPositionKeyValue(Integer limit,String position);

    List<Map<String,Object>> getPositionKeyValue(Integer limit,String position,List<String> city);

    Map<String, Integer> getKeyBySalaryPosition(String position, BigDecimal salaryMin, BigDecimal salaryMax, List<String> city);

    List<String> getAllKey(List<String> position);

    List<Map<String,Object>> getKeyByPosition(List<String> position);

}
