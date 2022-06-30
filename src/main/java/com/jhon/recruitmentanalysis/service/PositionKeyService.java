package com.jhon.recruitmentanalysis.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PositionKeyService {

    Map<String,Integer> getAllKeyValue(List<String> city);

    Map<String,Integer> getPositionKeyValue(String position);

    Map<String,Integer> getPositionKeyValue(String position,List<String> city);

    Map<String, Integer> getKeyBySalaryPosition(String position, BigDecimal salaryMin, BigDecimal salaryMax, List<String> city);

}
