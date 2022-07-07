package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.CompanyMapper;
import com.jhon.recruitmentanalysis.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    CompanyMapper companyMapper;

    @Override
    public List<Map> getCompanyScale(List<String> position) {
        return companyMapper.getCompanyScale(position);
    }

    @Override
    public List<Map> getCompanyType(List<String> position) {
        return companyMapper.getCompanyType(position);
    }
}
