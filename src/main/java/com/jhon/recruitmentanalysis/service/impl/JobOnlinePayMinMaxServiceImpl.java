package com.jhon.recruitmentanalysis.service.impl;

import com.jhon.recruitmentanalysis.mapper.JobOnlinePayMinMaxMapper;
import com.jhon.recruitmentanalysis.pojo.JobOnlinePayMinMax;
import com.jhon.recruitmentanalysis.service.JobOnlinePayMinMaxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobOnlinePayMinMaxServiceImpl implements JobOnlinePayMinMaxService {

    @Resource
    private JobOnlinePayMinMaxMapper jobOnlinePayMinMaxMapper;

    @Override
    public List<JobOnlinePayMinMax> findAllJobOnlinePayMinMax(Integer limit) {
        return jobOnlinePayMinMaxMapper.findAllJobOnlinePayMinMax(limit);
    }

    @Override
    public List<JobOnlinePayMinMax> findAllJobOnlinePayMinMax(Integer limit, List<String> city) {
        return jobOnlinePayMinMaxMapper.postFindAllJobOnlinePayMinMax(limit, city);
    }

}
