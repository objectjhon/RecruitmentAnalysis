package com.jhon.recruitmentanalysis.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CityEducationService {

    List<Map> getCityEducation();

    List<Map> getCityEducation(List<String> city);

    List<Map> getEducationBySalary(BigDecimal minSalary,BigDecimal maxSalary);

    List<Map> getCityBySalary(BigDecimal minSalary,BigDecimal maxSalary);

    List<Map> getPositionNumByCity();

    List<Map> getPositionNumByCity(List<String> city);

}
