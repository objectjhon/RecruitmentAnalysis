package com.jhon.recruitmentanalysis.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CompanyMapper {

    List<Map> getCompanyScale(@Param("position") List<String> position);

    List<Map> getCompanyType(@Param("position") List<String> position);

}
