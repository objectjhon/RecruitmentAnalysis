package com.jhon.recruitmentanalysis.service;

import java.util.List;
import java.util.Map;

public interface CompanyService {

    List<Map> getCompanyScale(List<String> position);

    List<Map> getCompanyType(List<String> position);

}
