package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.JobOnlinePayMapper;
import com.jhon.recruitmentanalysis.pojo.JobOnlinePay;
import com.jhon.recruitmentanalysis.service.JobOnlinePayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobOnlinePayServiceImpl implements JobOnlinePayService {

    @Resource
    private JobOnlinePayMapper jobOnlinePayMapper;

    @Override
    public List<JobOnlinePay> findAllJobOnlinePay(Integer limit) {
        return jobOnlinePayMapper.findAllJobOnlinePay(limit);
    }

    @Override
    public List<JobOnlinePay> findAllJobOnlinePay(Integer limit, List<String> city) {
        return jobOnlinePayMapper.postFindAllJobOnlinePay(limit, city);
    }

}
